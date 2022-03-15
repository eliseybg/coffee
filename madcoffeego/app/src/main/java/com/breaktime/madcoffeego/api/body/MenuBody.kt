package com.breaktime.madcoffeego.api.body

import com.google.gson.annotations.SerializedName

data class MenuBody(
    @SerializedName("id")
    val id: Int = 13
)