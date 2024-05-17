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
    ): UserDataDTO

    suspend fun userLogIn(
        email: String,
        password: String,
        acceptHeader: String = "application/json",
    ): UserDataDTO

    suspend fun getCategories(
    ): CategoriesDTO

    suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ): TrendingSellersDTO

    suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ): PopularSellersDTO
}