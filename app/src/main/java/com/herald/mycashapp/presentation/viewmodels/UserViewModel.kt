package com.herald.mycashapp.presentation.viewmodels

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.UserDataModel
import com.herald.mycashapp.domain.usecases.UserLoginUseCase
import com.herald.mycashapp.domain.usecases.UserSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase, private val userSignUpUseCase: UserSignUpUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserDataState>()
    val state: LiveData<UserDataState> = _state

    fun userLogIn(emailET: EditText, passwordET: EditText) {
        val email = emailET.text.toString()
        val password = passwordET.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            _state.value = UserDataState(error = Constants.MESSAGE_FORM_INCOMPLETE)
            return
        }
        userLoginUseCase(email, password).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = UserDataState(isLoading = true)
                }

                is Resources.Success -> {
                    _state.value = UserDataState(userData = it.data)
                }

                is Resources.Error -> {
                    _state.value = UserDataState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun userSignUp(
        nameET: EditText,
        emailET: EditText,
        phoneET: EditText,
        passwordET: EditText,
        passwordConfirmET: EditText
    ) {
        val name = nameET.text.toString()
        val email = emailET.text.toString()
        val phone = phoneET.text.toString()
        val password = passwordET.text.toString()
        val passwordConfirm = passwordConfirmET.text.toString()

        if (name.isEmpty() || email.isEmpty() || phone.length < 11 || password.length < 8 || password != passwordConfirm) {
            _state.value = UserDataState(error = Constants.MESSAGE_FORM_INCOMPLETE)
            return
        }

        userSignUpUseCase(name, email, password, phone).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = UserDataState(isLoading = true)
                }

                is Resources.Success -> {
                    _state.value = UserDataState(userData = it.data!!)
                }

                is Resources.Error -> {
                    _state.value = UserDataState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _state.value = UserDataState()
    }

    data class UserDataState(
        val isLoading: Boolean = false,
        val userData: UserDataModel? = null,
        val error: String? = null
    )
}