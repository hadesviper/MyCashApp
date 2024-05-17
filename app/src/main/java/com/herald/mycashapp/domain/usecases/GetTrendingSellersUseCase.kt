package com.herald.mycashapp.domain.usecases

import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.TrendingSellersModel
import com.herald.mycashapp.domain.repository.RetroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTrendingSellersUseCase @Inject constructor(
    private val retroRepo: RetroRepo
) {

    operator fun invoke(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ): Flow<Resources<TrendingSellersModel>> = flow {
        try {
            emit(Resources.Loading())
            val data = retroRepo.getTrendingSellers(
                latitude = latitude,
                longitude = longitude,
                filter = filter
            )
            emit(Resources.Success(data.toTrendingSellersModel()))
        } catch (e: HttpException) {
            emit(Resources.Error(message = e.message.toString()))
        } catch (e: IOException) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}