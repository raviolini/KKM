package com.example.kkm.ui.fragment

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
import com.example.kkm.R
import com.example.kkm.databinding.FragmentHomeBinding
import com.example.kkm.misc.MyStates
import com.example.kkm.misc.ShowStates
import com.example.kkm.ui.adapter.ItemAdapter
import com.example.kkm.ui.vm.HomeVM
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
                            resourceStats.data?.let { items ->
                                if (!items.isNullOrEmpty()) {
                                    FancyToast.makeText(context, "found!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true)
                                    homeSuccess(bindingHome)
                                    homeAdapter.setItemData(items)
                                } else {
                                    homeError(bindingHome, null)
                                    FancyToast.makeText(context, "something happened", FancyToast.LENGTH_LONG, FancyToast.ERROR, true)
                                }
                            }
                        }
                        MyStates.IS_LOADING -> {
                            homeLoading(bindingHome)
                            FancyToast.makeText(context, "loading", FancyToast.LENGTH_LONG, FancyToast.INFO, true)
                        }

                        MyStates.IS_ERROR ->{
                            FancyToast.makeText(context, it.message, FancyToast.LENGTH_LONG, FancyToast.ERROR, true)
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
            progressBar.visibility = visible
            recyclerHome.visibility = gone
        }
        return super.homeLoading(bindingHome)
    }

    override fun homeSuccess(bindingHome: FragmentHomeBinding): Int? {
        bindingHome.apply {
            errorLayout.mainNotFound.visibility = gone
            progressBar.visibility = gone
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

            progressBar.visibility = gone
            recyclerHome.visibility = gone
        }
        return super.homeError(bindingHome, message)
    }
}