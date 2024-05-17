package com.herald.mycashapp.data.remote

import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.data.remote.dto.CategoriesDTO
import com.herald.mycashapp.data.remote.dto.PopularSellersDTO
import com.herald.mycashapp.data.remote.dto.TrendingSellersDTO
import com.herald.mycashapp.data.remote.dto.UserDataDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroService {

    @FormUrlEncoded
    @POST("client-register")
    suspend fun userSignUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("device_token") deviceToken: String = Constants.DEVICE_TOKEN,
        @Header("lang") langHeader: String = "ar"
    ): UserDataDTO

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogIn(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String = Constants.DEVICE_TOKEN,
        @Header("Accept") acceptHeader: String = "application/json",
        @Header("lang") langHeader: String = "ar"
    ): UserDataDTO

    @GET("base-categories")
    suspend fun getCategories(
        @Header("Accept") acceptHeader: String = "application/json",
        @Header("Authorization") authorizationHeader: String = Constants.BEARER_AUTH,
        @Header("lang") langHeader: String = "ar"
    ): CategoriesDTO

    @GET("trending-sellers")
    suspend fun getTrendingSellers(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int,
        @Header("Accept") acceptHeader: String = "application/json",
        @Header("Authorization") authorizationHeader: String = Constants.BEARER_AUTH,
        @Header("lang") langHeader: String = "ar"
    ): TrendingSellersDTO

    @GET("popular-sellers")
    suspend fun getPopularSellers(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int,
        @Header("Accept") acceptHeader: String = "application/json",
        @Header("Authorization") authorizationHeader: String = Constants.BEARER_AUTH,
        @Header("lang") langHeader: String = "ar"
    ): PopularSellersDTO
}