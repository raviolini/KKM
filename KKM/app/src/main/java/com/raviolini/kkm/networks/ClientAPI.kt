package com.raviolini.kkm.networks

import com.raviolini.kkm.data.kos.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientAPI {
    @GET("kosts")
    suspend fun getAllKost() : List<Item>

    @GET("kosts")
    suspend fun searchForItem(
        @Query("q")q: String?
    ): List<Item>

}