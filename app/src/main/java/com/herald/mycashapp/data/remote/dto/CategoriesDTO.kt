package com.herald.mycashapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.herald.mycashapp.domain.models.CategoriesModel

data class CategoriesDTO(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("response_code")
    val responseCode: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
) {
    data class Data(
        @SerializedName("active")
        val active: Int = 0,
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("image")
        val image: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("name_ar")
        val nameAr: String = "",
        @SerializedName("name_en")
        val nameEn: String = ""
    )

    fun toCategoriesModel(): CategoriesModel {
        return CategoriesModel(
            data = data.map { data ->
                CategoriesModel.Data(
                    id = data.id,
                    image = data.image,
                    nameEn = data.nameEn
                )
            }
        )
    }
}