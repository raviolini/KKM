package com.example.kkm.networks

import com.example.kkm.data.kos.Item
import retrofit2.http.GET

interface ClientAPI {
    @GET("kosts")
    suspend fun getAllKost() : Item

}