package com.herald.mycashapp.domain.repository

import com.herald.mycashapp.data.remote.dto.CategoriesDTO
import com.herald.mycashapp.data.remote.dto.PopularSellersDTO
import com.herald.mycashapp.data.remote.dto.TrendingSellersDTO
import com.herald.mycashapp.data.remote.dto.UserDataDTO

interface RetroRepo {

    suspend fun userSignUp(
        name: String,
        email: String,
        password: String,
        phone: String,
        deviceToken: String = "",
        langHeader: String = "ar"
    ): UserDataDTO

    suspend fun userLogIn(
        email: String,
        password: String,
        deviceToken: String = "12233454566787877",
        acceptHeader: String = "application/json",
        langHeader: String = "ar"
    ): UserDataDTO

    suspend fun getCategories(
        authorizationHeader: String,
        langHeader: String = "ar"
    ): CategoriesDTO

    suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
        authorizationHeader: String,
        langHeader: String = "ar"
    ): TrendingSellersDTO

    suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
        authorizationHeader: String,
        langHeader: String = "ar"
    ): PopularSellersDTO
}