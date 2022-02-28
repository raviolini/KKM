package com.raviolini.kkm.data

import androidx.lifecycle.liveData
import com.raviolini.kkm.misc.ResourceStats
import com.raviolini.kkm.networks.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object ItemRepos {

    fun searchItem(query : String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try {
            val itemSearch = RetrofitConfig.CLIENT_API.searchForItem(query)
            emit(ResourceStats.onSuccess(itemSearch))
        }catch (e : Exception ){
            emit(ResourceStats.onError(null, e.message ?: "Error Detected : ${e.localizedMessage}"))
        }
    }
}