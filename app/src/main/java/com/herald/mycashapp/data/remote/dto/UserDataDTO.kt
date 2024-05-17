package com.herald.mycashapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.herald.mycashapp.domain.models.UserDataModel

data class UserDataDTO(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("response_code")
    val responseCode: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
) {
    data class Data(
        @SerializedName("addresses")
        val addresses: List<Addresses>? = listOf(),
        @SerializedName("balance")
        val balance: String = "",
        @SerializedName("email")
        val email: String = "",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("image")
        val image: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("phone")
        val phone: String = "",
        @SerializedName("status")
        val status: Int = 0,
        @SerializedName("token")
        val token: String = "",
        @SerializedName("type")
        val type: Int = 0
    ) {
        data class Addresses(
            @SerializedName("address")
            val address: String? = "",
            @SerializedName("apartment")
            val apartment: String = "",
            @SerializedName("building")
            val building: String = "",
            @SerializedName("floor")
            val floor: Any? = null,
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("lat")
            val lat: String = "",
            @SerializedName("lng")
            val lng: String = "",
            @SerializedName("name")
            val name: Any? = null,
            @SerializedName("street")
            val street: String = ""
        )
    }

    fun toUserDataModel(): UserDataModel {
        return UserDataModel(
            userID = data.id,
            name = data.name,
            address = data.addresses?.getOrNull(0)?.address.orEmpty(),
            phone = data.phone,
            token = data.token,
            lng = data.addresses?.getOrNull(0)?.lat?.toDouble() ?: 0.0,
            lat = data.addresses?.getOrNull(0)?.lng?.toDouble() ?: 0.0
        )
    }
}