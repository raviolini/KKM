package com.raviolini.kkm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.PagerAdapter
import com.raviolini.kkm.data.kos.Item
import com.raviolini.kkm.databinding.FragmentDetailBinding
import com.raviolini.kkm.misc.ShowStates
import com.raviolini.kkm.ui.vm.DetailVM

class FragmentDetail : Fragment(), ShowStates {
    private lateinit var bindingDetail : FragmentDetailBinding
    private lateinit var pagerArgs: PagerAdapter
    private lateinit var detailVM: DetailVM
    private lateinit var item : Item
    private val args : FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailVM = ViewModelProvider(
            this
        )[DetailVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)
        //bindingDetail.lifecycleOwner = viewLifecycleOwner

        return bindingDetail.root
    }
}