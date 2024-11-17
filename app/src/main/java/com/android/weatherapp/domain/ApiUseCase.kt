package com.android.weatherapp.domain

import com.android.weatherapp.common.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ApiUseCase(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(lat: String, lon: String) : Flow<Resource<WeatherDetails>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(apiRepository.getItems(lat, lon)))
        }
        catch (e: IOException) {
            emit(Resource.Error(error = "No Internet Access, Please Check Your Internet Connection"))
        }
        catch (e: Exception) {
            if (e is CancellationException) throw e
            else emit(Resource.Error(error = "SomeThing Went Wrong Try Again Later!"))
        }
    }

}