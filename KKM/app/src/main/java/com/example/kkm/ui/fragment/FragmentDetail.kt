package com.example.kkm.ui.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.PagerAdapter
import com.example.kkm.data.kos.Item
import com.example.kkm.databinding.FragmentDetailBinding
import com.example.kkm.misc.ShowStates
import com.example.kkm.ui.vm.DetailVM

class FragmentDetail : Fragment(), ShowStates {
    private lateinit var bindingDetail : FragmentDetailBinding
    private lateinit var pagerArgs: PagerAdapter
    private lateinit var detailVM: DetailVM
    private lateinit var item : Item
    private val args : FragmentDetailArgs by navArgs()
}