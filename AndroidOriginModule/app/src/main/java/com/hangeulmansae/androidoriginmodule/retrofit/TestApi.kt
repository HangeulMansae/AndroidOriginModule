package com.hangeulmansae.androidoriginmodule.retrofit

import com.hangeulmansae.androidoriginmodule.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface TestApi {
    @GET("1/")
    suspend fun testApi(): Response<String>
}