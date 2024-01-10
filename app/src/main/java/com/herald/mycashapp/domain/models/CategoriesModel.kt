package com.herald.mycashapp.domain.models


data class CategoriesModel(
    val data: List<Data> = listOf()
){
    data class Data(
        val id: Int = 0,
        val image: String = "",
        val nameEn: String = ""
    )
}
