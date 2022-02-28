package com.raviolini.kkm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raviolini.kkm.R
import com.raviolini.kkm.databinding.FragmentHomeBinding
import com.raviolini.kkm.misc.MyStates
import com.raviolini.kkm.misc.ShowStates
import com.raviolini.kkm.ui.adapter.ItemAdapter
import com.raviolini.kkm.ui.vm.HomeVM
import com.shashank.sony.fancytoastlib.FancyToast

class FragmentHome  : Fragment(), ShowStates{
    private lateinit var bindingHome : FragmentHomeBinding
    private lateinit var homeAdapter : ItemAdapter
    private val homeVM : HomeVM by navGraphViewModels(R.id.my_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bindingHome.errorLayout.emptyText.text = getString(R.string.search_for_kos)

        homeAdapter = ItemAdapter(arrayListOf()){ name, iv ->
            findNavController().navigate(FragmentHomeDirections.detailsAction(name),
                FragmentNavigatorExtras(
                    iv to name
                )
            )
        }
        bindingHome.progressBar.visibility = View.VISIBLE
        bindingHome.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

        bindingHome.searchBar.apply {
            queryHint = getString(R.string.search_placeholderHint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    homeVM.setForForSearch(query)
                    bindingHome.searchBar.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        observeHome()

    }

    private fun observeHome(){
        homeVM.searchResult.observe(viewLifecycleOwner) {
            homeVM.searchResult.observe(viewLifecycleOwner) {
                it?.let { resourceStats ->
                    when (resourceStats.states) {
                        MyStates.IS_SUCCESS -> {
                            bindingHome.progressBar.visibility = gone
                            resourceStats.data?.let { items ->
                                if (!items.isNullOrEmpty()) {
                                    FancyToast.makeText(context, "found!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show()
                                    homeSuccess(bindingHome)
                                    homeAdapter.setItemData(items)
                                } else {
                                    homeError(bindingHome, null)
                                    FancyToast.makeText(context, "something happened", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                                }
                            }
                        }
                        MyStates.IS_LOADING -> {
                            bindingHome.progressBar.visibility = visible
                            homeLoading(bindingHome)

                            FancyToast.makeText(context, "loading", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()

                        }

                        MyStates.IS_ERROR ->{
                            bindingHome.progressBar.visibility = gone
                            FancyToast.makeText(context, it.message, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                            homeError(bindingHome, it.message)
                        }
                    }
                }
            }
        }
    }

    override fun homeLoading(bindingHome: FragmentHomeBinding): Int? {
        bindingHome.apply {
            errorLayout.mainNotFound.visibility = gone

            recyclerHome.visibility = gone
        }
        return super.homeLoading(bindingHome)
    }

    override fun homeSuccess(bindingHome: FragmentHomeBinding): Int? {
        bindingHome.apply {
            errorLayout.mainNotFound.visibility = gone

            recyclerHome.visibility = visible
        }
        return super.homeSuccess(bindingHome)
    }

    override fun homeError(bindingHome: FragmentHomeBinding, message: String?): Int? {
        bindingHome.apply {
            errorLayout.apply {
                mainNotFound.visibility = visible
                emptyText.visibility = gone
                //emptyText.text = message ?: getString(R.string.item_not_found_txt)
            }


            recyclerHome.visibility = gone
        }
        return super.homeError(bindingHome, message)
    }
}