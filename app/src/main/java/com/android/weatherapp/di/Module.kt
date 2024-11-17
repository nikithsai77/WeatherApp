package com.android.weatherapp.di

import android.app.Application
import com.android.weatherapp.data.ApiRepositoryImpl
import com.android.weatherapp.data.ApiService
import com.android.weatherapp.domain.ApiRepository
import com.android.weatherapp.domain.ApiUseCase
import com.android.weatherapp.domain.CityAndStateUseCase
import com.android.weatherapp.domain.LocationClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationClient(application: Application): LocationClient {
        return LocationClient(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService) : ApiRepository {
        return ApiRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideApiUseCase(apiRepository: ApiRepository) : ApiUseCase {
        return ApiUseCase(apiRepository)
    }

    @Singleton
    @Provides
    fun provideCityAndStateUseCase(apiRepository: ApiRepository) : CityAndStateUseCase {
        return CityAndStateUseCase(apiRepository)
    }
}