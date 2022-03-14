package com.breaktime.madcoffeego

import android.app.Application
import android.os.Build
import com.breaktime.madcoffeego.api.RetrofitInstance
import com.breaktime.madcoffeego.api.body.RegisterDeviceBody
import com.breaktime.madcoffeego.data.LocalStorage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val phoneRegisterData = getPhoneRegisterData()
        registerPhone(phoneRegisterData)
    }

    private fun getPhoneRegisterData(): RegisterDeviceBody {
        val localStorage = LocalStorage(this)
        val uuid = localStorage.uuid
        val appId = applicationContext.packageName
        val device = Build.MODEL
        return RegisterDeviceBody(uuid, appId, device)
    }

    private fun registerPhone(phoneData: RegisterDeviceBody) = MainScope().launch {
        RetrofitInstance.API.registerDevice(phoneData)
    }
}
