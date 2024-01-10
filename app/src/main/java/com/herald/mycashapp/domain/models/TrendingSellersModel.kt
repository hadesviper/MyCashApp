package com.herald.mycashapp.domain.models

data class TrendingSellersModel(
    val data: List<Data> = listOf()
){
    data class Data(
        val logo: String = ""
    )
}
