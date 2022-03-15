package com.breaktime.madcoffeego.api

import com.breaktime.madcoffeego.api.body.*
import retrofit2.Call
import retrofit2.http.*

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

    @GET("menu")
    fun getMenu(@Query("id") id: Int = 13): Call<List<MenuItem>>
}