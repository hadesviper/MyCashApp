package com.herald.mycashapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.CategoriesModel
import com.herald.mycashapp.domain.usecases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CategoriesState>()
    val state: LiveData<CategoriesState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = CategoriesState(isLoading = true)
                }

                is Resources.Success -> {
                    _state.value = CategoriesState(categories = it.data!!)
                }

                is Resources.Error -> {
                    _state.value = CategoriesState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
    data class CategoriesState (
        val isLoading:Boolean = false,
        val categories:CategoriesModel? = CategoriesModel(),
        val error:String? = null
    )
}