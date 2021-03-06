package com.raviolini.kkm.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raviolini.core.domain.model.Item
import com.raviolini.core.domain.usecase.ItemUseCase
import kotlinx.coroutines.launch

class DetailVM(private val itemUseCase: ItemUseCase) : ViewModel(){

    fun detailItem(name : String) = itemUseCase.getDetailKos(name).asLiveData()

    fun insertFav(item:Item) = viewModelScope.launch {
        itemUseCase.insertItem(item)
    }

    fun deleteFav(item : Item) = viewModelScope.launch {
        itemUseCase.deleteItem(item)
    }

    fun getDetailState(name : String) = itemUseCase.getDetailState(name)?.asLiveData()
}