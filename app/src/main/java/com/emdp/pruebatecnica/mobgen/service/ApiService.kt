package com.emdp.pruebatecnica.mobgen.service

import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse
import com.emdp.pruebatecnica.mobgen.model.api.CategoriesResponse
import com.emdp.pruebatecnica.mobgen.model.api.CharactersResponse
import com.emdp.pruebatecnica.mobgen.model.api.HousesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoriesResponse>>

    @GET("list/1")
    suspend fun getBooks(): Response<List<BooksResponse>>

    @GET("list/2")
    suspend fun getHouses(): Response<List<HousesResponse>>

    @GET("list/3")
    suspend fun getCharacters(): Response<List<CharactersResponse>>
}