package com.example.mychucknoris.model

import com.google.gson.annotations.SerializedName

data class ChuckInfoDataSearch(
    @SerializedName("result") val result: List<ChuckResultSearch>,
    @SerializedName("total") val total: Int
)