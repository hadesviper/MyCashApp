package com.herald.mycashapp.data.remote.repository

import com.herald.mycashapp.data.remote.RetroService
import com.herald.mycashapp.data.remote.dto.CategoriesDTO
import com.herald.mycashapp.data.remote.dto.PopularSellersDTO
import com.herald.mycashapp.data.remote.dto.TrendingSellersDTO
import com.herald.mycashapp.data.remote.dto.UserDataDTO
import com.herald.mycashapp.domain.repository.RetroRepo
import javax.inject.Inject

class RetroImpl @Inject constructor(
    private val retroService: RetroService
) : RetroRepo {

    override suspend fun userSignUp(
        name: String,
        email: String,
        password: String,
        phone: String,
        deviceToken: String,
        langHeader: String
    ): UserDataDTO {
        return retroService.userSignUp(name, email, password, phone, deviceToken, langHeader)
    }

    override suspend fun userLogIn(
        email: String,
        password: String,
        deviceToken: String,
        acceptHeader: String,
        langHeader: String
    ): UserDataDTO {
        return retroService.userLogIn(email, password, deviceToken, acceptHeader, langHeader)
    }

    override suspend fun getCategories(
        authorizationHeader: String,
        langHeader: String
    ): CategoriesDTO {
        return retroService.getCategories(authorizationHeader, langHeader)
    }

    override suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
        authorizationHeader: String,
        langHeader: String
    ): TrendingSellersDTO {
        return retroService.getTrendingSellers(
            latitude,
            longitude,
            filter,
            authorizationHeader,
            langHeader
        )
    }

    override suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
        authorizationHeader: String,
        langHeader: String
    ): PopularSellersDTO {
        return retroService.getPopularSellers(
            latitude,
            longitude,
            filter,
            authorizationHeader,
            langHeader
        )
    }
}