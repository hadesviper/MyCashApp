package com.herald.mycashapp.domain.models

data class UserDataModel(
    val userID: Int,
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val token: String = "",
    val lng: Double = 0.0,
    val lat: Double = 0.0
)