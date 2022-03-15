package com.breaktime.madcoffeego.api

import com.breaktime.madcoffeego.api.body.AccessToken
import com.breaktime.madcoffeego.api.body.PhoneKey
import com.breaktime.madcoffeego.api.body.RegisterDeviceBody
import com.breaktime.madcoffeego.api.body.SpecialDealsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CoffeeApi {
    @POST("mobile")
    fun registerDevice(@Body params: RegisterDeviceBody): Call<String>

    @GET("user")
    fun getPhoneKey(@Header("phone") phone: String): Call<PhoneKey>

    @GET("user")
    fun checkPhoneKey(
        @Header("phone") phone: String,
        @Header("phonekey") phonekey: String,
        @Header("uuid") uuid: String,
    ): Call<AccessToken>

    @GET("action")
    fun getSpecialDeals(): Call<List<SpecialDealsItem>>
}