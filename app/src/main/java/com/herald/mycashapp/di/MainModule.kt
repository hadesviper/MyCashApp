package com.herald.mycashapp.di

import android.content.Context
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.data.remote.RetroService
import com.herald.mycashapp.data.remote.repository.RetroImpl
import com.herald.mycashapp.domain.repository.RetroRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Singleton
    @Provides
    fun getRetrofitInstance(@ApplicationContext context:Context ): Retrofit {

        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

   @Singleton
    @Provides
    fun getRestApiService(retrofit: Retrofit): RetroService {
        return retrofit.create(RetroService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(retroService: RetroService): RetroRepo {
        return RetroImpl(retroService)
    }
}