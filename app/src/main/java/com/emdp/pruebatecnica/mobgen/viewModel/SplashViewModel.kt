package com.emdp.pruebatecnica.mobgen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.database.GameOfThronesDb
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import com.emdp.pruebatecnica.mobgen.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SplashViewModel(app: Application) : AndroidViewModel(app) {

    private var categories: MutableLiveData<List<Categories>> = MutableLiveData()
    private var categoriesList: MutableList<Categories> = arrayListOf()

    init {
        loadCategories()
    }

    fun getCategories() : LiveData<List<Categories>> {
        return categories
    }

    private fun loadCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            val utils : Utils.Companion = Utils
            val call = utils.retrofit.create(ApiService::class.java).getCategories()
            val categoriesResponseList = call.body()

            run {
                if (call.isSuccessful) {
                    val categoriesDao = GameOfThronesDb
                        .getAppDatabase(getApplication())?.categoriesDao()
                    var category: Categories

                    categoriesResponseList?.forEach {
                        category = Categories(it.categoryName, it.type)
                        categoriesList.add(category)
                    }

                    // Insert data into database
                    val categoriesDb : List<Categories>? = categoriesDao?.getAllCategories()

                    if (null == categoriesDb || categoriesDb.isEmpty()) {
                        categoriesDao?.insertCategories(categoriesList)
                    } else {
                        // FIXME - Optimize this function
                        categoriesList.forEach { category ->
                            if (null == categoriesDao?.getCategoryByType(category.type)) {
                                categoriesDao?.insertCategory(category)
                            }
                        }
                    }

                    // Send to view a list of categories
                    categories.postValue(categoriesList)
                }
            }
        }
    }
}