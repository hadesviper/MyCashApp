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
        phone: String
    ): UserDataDTO {
        return retroService.userSignUp(name, email, password, phone)
    }

    override suspend fun userLogIn(
        email: String,
        password: String,
        acceptHeader: String
    ): UserDataDTO {
        return retroService.userLogIn(email, password, acceptHeader)
    }

    override suspend fun getCategories(
    ): CategoriesDTO {
        return retroService.getCategories()
    }

    override suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ): TrendingSellersDTO {
        return retroService.getTrendingSellers(
            latitude,
            longitude,
            filter
        )
    }

    override suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ): PopularSellersDTO {
        return retroService.getPopularSellers(
            latitude,
            longitude,
            filter
        )
    }
}