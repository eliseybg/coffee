package com.breaktime.madcoffeego.api.body

data class MenuItem(
    val category: String,
    val id: String,
    val image: String,
    val name: String,
    val price: String
)