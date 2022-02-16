package com.example.kkm.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kkm.data.kos.Item
import com.example.kkm.misc.ResourceStats

class DetailVM : ViewModel(){

    private val itemName : MutableLiveData<String> = MutableLiveData()

}