package com.herald.mycashapp.domain.models

data class PopularSellersModel(
    val data: List<Data> = listOf()
){
    data class Data(
        val id: Int = 0,
        val image: String = "",
        val name: String = "",
        val isFavorite: Boolean = false,
        val rate: String = "",
        val distance: String = ""
    )
}
