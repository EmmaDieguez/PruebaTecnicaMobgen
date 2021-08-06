package com.emdp.pruebatecnica.mobgen.viewModel

import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdp.pruebatecnica.mobgen.database.GameOfThronesDb
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app)  {

    private var categories: MutableLiveData<List<Categories>> = MutableLiveData()

    init {
        loadAllCategoriesDb()
    }

    fun getAllCategories() : LiveData<List<Categories>> {
        return categories
    }

    private fun loadAllCategoriesDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val categoriesDao = GameOfThronesDb
                .getAppDatabase(getApplication())?.categoriesDao()
            val categoriesDb = categoriesDao?.getAllCategories()!!
            categories.postValue(categoriesDb)
        }
    }
}