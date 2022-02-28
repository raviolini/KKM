package com.raviolini.kkm.networks

import android.os.Parcelable
import com.raviolini.kkm.data.kos.Item
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultResponds(
    val total_count : String,
    val incomplete_result : Boolean? = null,
    val items : List<Item>
):Parcelable