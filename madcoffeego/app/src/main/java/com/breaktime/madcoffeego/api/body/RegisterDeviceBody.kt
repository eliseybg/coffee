package com.breaktime.madcoffeego.api.body

import com.google.gson.annotations.SerializedName

data class RegisterDeviceBody(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("appId")
    val appId: String,
    @SerializedName("device")
    val device: String
)
