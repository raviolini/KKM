package com.example.kkm.data

import androidx.lifecycle.liveData
import com.example.kkm.misc.ResourceStats
import com.example.kkm.networks.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import java.lang.Exception

object ItemRepos {

    fun searchItem(query : String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try {
            val itemSearch = RetrofitConfig.CLIENT_API.searchForItem(query)
        }catch (e : Exception ){
            emit(ResourceStats.onError(null, e.message ?: "Error Detected : ${e.localizedMessage}"))
        }
    }
}