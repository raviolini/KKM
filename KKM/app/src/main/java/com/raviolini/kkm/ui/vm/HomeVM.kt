package com.raviolini.kkm.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.raviolini.kkm.data.ItemRepos
import com.raviolini.kkm.data.kos.Item
import com.raviolini.kkm.misc.ResourceStats

class HomeVM  :  ViewModel(){
    private val itemName : MutableLiveData<String> = MutableLiveData()

    val searchResult : LiveData<ResourceStats<List<Item>>> = Transformations
        .switchMap(itemName){
            ItemRepos.searchItem(it)
        }

    fun setForForSearch(Query : String){
        if (itemName.value == Query){
            return
        }
        itemName.value = Query
    }
}