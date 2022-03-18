package com.raviolini.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raviolini.core.domain.usecase.ItemUseCase

class FavoriteVM(itemUseCase: ItemUseCase) : ViewModel(){
    val favoriteItem = itemUseCase.getFavoriteItem().asLiveData()
}
