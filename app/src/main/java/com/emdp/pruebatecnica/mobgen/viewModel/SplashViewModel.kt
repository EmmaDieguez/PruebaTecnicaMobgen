package com.emdp.pruebatecnica.mobgen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdp.pruebatecnica.mobgen.model.api.CategoriesResponse
import com.emdp.pruebatecnica.mobgen.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SplashViewModel : ViewModel() {

    private var categories: MutableLiveData<List<CategoriesResponse>> = MutableLiveData()

    private var httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private var okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://private-anon-f141a44db8-androidtestmobgen.apiary-mock.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init {
        loadCategories()
    }

    fun getCategories() : LiveData<List<CategoriesResponse>> {
        return categories
    }

    private fun loadCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = retrofit.create(ApiService::class.java).getCategories()
            val categoriesList = call.body()

            run {
                if (call.isSuccessful) {
                    categories.postValue(categoriesList)
                }
            }
        }
    }
}