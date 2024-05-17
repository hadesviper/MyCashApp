package com.herald.mycashapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.mycashapp.common.Resources
import com.herald.mycashapp.domain.models.CategoriesModel
import com.herald.mycashapp.domain.models.PopularSellersModel
import com.herald.mycashapp.domain.models.TrendingSellersModel
import com.herald.mycashapp.domain.usecases.GetCategoriesUseCase
import com.herald.mycashapp.domain.usecases.GetPopularSellersUseCase
import com.herald.mycashapp.domain.usecases.GetTrendingSellersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getPopularSellersUseCase: GetPopularSellersUseCase,
    private val getTrendingSellersUseCase: GetTrendingSellersUseCase
) : ViewModel() {

    private val _stateCategories = MutableLiveData<CategoriesState>()
    val stateCategories: LiveData<CategoriesState> = _stateCategories

    private val _statePopular = MutableLiveData<PopularSellersState>()
    val statePopular: LiveData<PopularSellersState> = _statePopular

    private val _stateTrending = MutableLiveData<TrendingSellersState>()
    val stateTrending: LiveData<TrendingSellersState> = _stateTrending



    fun getCategories() {
        getCategoriesUseCase().onEach {
            when (it) {
                is Resources.Loading -> {
                    _stateCategories.value = CategoriesState(isLoading = true)
                }

                is Resources.Success -> {
                    _stateCategories.value = CategoriesState(categories = it.data!!)
                }

                is Resources.Error -> {
                    _stateCategories.value = CategoriesState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ) {
        getPopularSellersUseCase(
            latitude = latitude, longitude = longitude, filter = filter

        ).onEach {
            when (it) {
                is Resources.Loading -> {
                    _statePopular.value = PopularSellersState(isLoading = true)
                }

                is Resources.Success -> {
                    _statePopular.value = PopularSellersState(popular = it.data!!)
                }

                is Resources.Error -> {
                    _statePopular.value = PopularSellersState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int,
    ) {
        getTrendingSellersUseCase(
            latitude = latitude, longitude = longitude, filter = filter
        ).onEach {
            when (it) {
                is Resources.Loading -> {
                    _stateTrending.value = TrendingSellersState(isLoading = true)
                }

                is Resources.Success -> {
                    _stateTrending.value = TrendingSellersState(trending = it.data!!)
                }

                is Resources.Error -> {
                    _stateTrending.value = TrendingSellersState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetState(){
        _stateCategories.value = CategoriesState()
        _statePopular.value = PopularSellersState()
        _stateTrending.value = TrendingSellersState()
    }

    data class CategoriesState(
        val isLoading: Boolean = false,
        val categories: CategoriesModel? = null,
        val error: String? = null
    )

    data class PopularSellersState(
        val isLoading: Boolean = false,
        val popular: PopularSellersModel? = null,
        val error: String? = null
    )

    data class TrendingSellersState(
        val isLoading: Boolean = false,
        val trending: TrendingSellersModel? = null,
        val error: String? = null
    )
}