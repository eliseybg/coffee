package com.breaktime.madcoffeego.api

import com.breaktime.madcoffeego.api.body.RegisterDeviceBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CoffeeApi {
    @POST("mobile")
    fun registerDevice(@Body params: RegisterDeviceBody): Call<String>
}