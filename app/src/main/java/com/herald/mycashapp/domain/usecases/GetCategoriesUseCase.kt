package com.herald.mycashapp.domain.usecases

import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.CategoriesModel
import com.herald.mycashapp.domain.repository.RetroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val retroRepo: RetroRepo
) {

    operator fun invoke(): Flow<Resources<CategoriesModel>> = flow {
        try {
            emit(Resources.Loading())
            val data = retroRepo.getCategories()
            emit(Resources.Success(data.toCategoriesModel()))
        } catch (e: HttpException) {
            emit(Resources.Error(message = e.message.toString()))
        } catch (e: IOException) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}