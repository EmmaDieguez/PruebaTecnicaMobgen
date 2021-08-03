package com.emdp.pruebatecnica.mobgen.service

import com.emdp.pruebatecnica.mobgen.model.api.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoriesResponse>>
}