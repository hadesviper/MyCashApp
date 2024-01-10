package com.herald.mycashapp.domain.usecases

import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.UserDataModel
import com.herald.mycashapp.domain.repository.RetroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
    private val retroRepo: RetroRepo
) {

    operator fun invoke(
        name: String,
        email: String,
        password: String,
        phone: String,
    ): Flow<Resources<UserDataModel>> = flow {
        try {
            emit(Resources.Loading())
            val data = retroRepo.userSignUp(
                email = email, password = password, phone = phone, name = name
            )
            if (data.success)
                emit(Resources.Success(data.toUserDataModel()))
            else
                emit(Resources.Error(message = data.message))
        } catch (e: HttpException) {
            emit(Resources.Error(message = e.message.toString()))
        } catch (e: IOException) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}