package com.breaktime.madcoffeego.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import java.util.*

const val PHONE_UUID_KEY = "unique phone uuid"

class LocalStorage(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MySharedPref", MODE_PRIVATE)

    val uuid: String
        get() {
            var id = sharedPreferences.getString(PHONE_UUID_KEY, null)
            if (id == null) {
                val myEdit = sharedPreferences.edit()
                id = UUID.randomUUID().toString()
                myEdit.putString(PHONE_UUID_KEY, id)
                myEdit.apply()
            }
            return id
        }
}