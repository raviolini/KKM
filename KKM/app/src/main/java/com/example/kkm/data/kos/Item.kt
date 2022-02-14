package com.example.kkm.data.kos

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(

    @field:Json(name = "id")
    val id : String,

    @field:Json(name = "name")
    val name : String,

    @field:Json(name = "description")
    val description : String,

    @field:Json(name = "address")
    val address : String,

    @field:Json(name = "imageUrl")
    val imageUrl: String,

    @field:Json(name = "genderRestriction")
    val genderRestriction : String,

    @field:Json(name = "availability")
    val availability : String,

    @field:Json(name = "bedrooms")
    val bedrooms : Int,

    @field:Json(name = "price")
    val price : Int
):Parcelable
