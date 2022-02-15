package com.example.kkm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.kkm.R
import com.example.kkm.databinding.FragmentHomeBinding
import com.example.kkm.misc.ShowStates
import com.example.kkm.ui.adapter.ItemAdapter
import com.example.kkm.ui.vm.HomeVM

class FragmentHome  : Fragment(), ShowStates{
    private lateinit var bindingHome : FragmentHomeBinding
    private lateinit var homeAdapter : ItemAdapter
    private val homeVM : HomeVM by navGraphViewModels(R.id.my_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHome = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bindingHome.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bindingHome.errorLayout.emptyText.text = getString(R.string.search_for_kos)

        /*homeAdapter = ItemAdapter(arrayListOf(){name, iv->
            findNavController().navigate(FragmentHomeDirections)
        })*/
    }
}