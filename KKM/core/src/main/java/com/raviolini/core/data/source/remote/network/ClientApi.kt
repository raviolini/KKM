package com.raviolini.core.data.source.remote.network

import com.raviolini.core.data.source.remote.response.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientApi {

    @GET("kosts")
    suspend fun searchForItem(
        @Query("q")q: String?
    ): List<ItemResponse>//ListItemResponse

   @GET("kosts")
    suspend fun searchForItemDetail(
        @Query("q")q: String?
    ): List<ItemResponse>

}