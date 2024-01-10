package com.herald.mycashapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.herald.mycashapp.domain.models.TrendingSellersModel

data class TrendingSellersDTO(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("response_code")
    val responseCode: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
) {
    data class Data(
        @SerializedName("data")
        val `data`: List<Data> = listOf(),
        @SerializedName("pagination")
        val pagination: Pagination = Pagination()
    ) {
        data class Data(
            @SerializedName("address")
            val address: String? = null,
            @SerializedName("appointments")
            val appointments: String? = null,
            @SerializedName("categories")
            val categories: List<Category> = listOf(),
            @SerializedName("description")
            val description: String? = null,
            @SerializedName("distance")
            val distance: Any? = null,
            @SerializedName("email")
            val email: String = "",
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("image")
            val image: String = "",
            @SerializedName("information")
            val information: Information = Information(),
            @SerializedName("is_favorite")
            val isFavorite: Boolean = false,
            @SerializedName("lat")
            val lat: String? = null,
            @SerializedName("lng")
            val lng: String? = null,
            @SerializedName("logo")
            val logo: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("phone")
            val phone: String = "",
            @SerializedName("popular")
            val popular: Int = 0,
            @SerializedName("product_categories")
            val productCategories: List<ProductCategory> = listOf(),
            @SerializedName("rate")
            val rate: String = "",
            @SerializedName("status")
            val status: Int = 0,
            @SerializedName("token")
            val token: String = "",
            @SerializedName("trending")
            val trending: Int = 0,
            @SerializedName("type")
            val type: Int = 0
        ) {
            data class Category(
                @SerializedName("active")
                val active: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("image")
                val image: String = "",
                @SerializedName("name")
                val name: String = ""
            )

            data class Information(
                @SerializedName("activity")
                val activity: Any? = null,
                @SerializedName("driving_image")
                val drivingImage: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("identity_number")
                val identityNumber: String = "",
                @SerializedName("nationality")
                val nationality: String = "",
                @SerializedName("tax_record")
                val taxRecord: String = "",
                @SerializedName("vehicle_image")
                val vehicleImage: String = "",
                @SerializedName("vehicle_registration")
                val vehicleRegistration: String = "",
                @SerializedName("vehicle_tablet_image")
                val vehicleTabletImage: String = ""
            )

            data class ProductCategory(
                @SerializedName("active")
                val active: Int = 0,
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("name_ar")
                val nameAr: String = "",
                @SerializedName("name_en")
                val nameEn: String = ""
            )
        }

        data class Pagination(
            @SerializedName("count")
            val count: Int = 0,
            @SerializedName("current_page")
            val currentPage: Int = 0,
            @SerializedName("is_pagination")
            val isPagination: Boolean = false,
            @SerializedName("per_page")
            val perPage: Int = 0,
            @SerializedName("total")
            val total: Int = 0,
            @SerializedName("total_pages")
            val totalPages: Int = 0
        )
    }
    fun toTrendingSellersModel(): TrendingSellersModel {
        return TrendingSellersModel(
            data = data.data.map { data ->
                TrendingSellersModel.Data(data.logo)
            }
        )
    }
}