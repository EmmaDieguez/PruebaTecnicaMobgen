package com.emdp.pruebatecnica.mobgen.viewModel

import android.R
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.database.GameOfThronesDb
import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse
import com.emdp.pruebatecnica.mobgen.model.api.CharactersResponse
import com.emdp.pruebatecnica.mobgen.model.api.HousesResponse
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import com.emdp.pruebatecnica.mobgen.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app)  {

    private var categories: MutableLiveData<List<Categories>> = MutableLiveData()
    private var books: MutableLiveData<List<BooksResponse>> = MutableLiveData()
    private var houses: MutableLiveData<List<HousesResponse>> = MutableLiveData()
    private var characters: MutableLiveData<List<CharactersResponse>> = MutableLiveData()

    private val utils : Utils.Companion = Utils

    init {
        loadAllCategoriesDb()
        loadAllBooks()
        loadAllHouses()
        loadAllCharacters()
    }

    fun getAllCategories() : LiveData<List<Categories>> {
        return categories
    }

    fun getAllBooks() : LiveData<List<BooksResponse>> {
        return books
    }

    fun getAllHouses() : LiveData<List<HousesResponse>> {
        return houses
    }

    fun getAllCharacters() : LiveData<List<CharactersResponse>> {
        return characters
    }

    private fun loadAllCategoriesDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val categoriesDao = GameOfThronesDb
                .getAppDatabase(getApplication())?.categoriesDao()
            val categoriesDb = categoriesDao?.getAllCategories()!!
            categories.postValue(categoriesDb)
        }
    }

    private fun loadAllBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = utils.retrofit.create(ApiService::class.java).getBooks()
            val booksResponseList = call.body()

            run {
                if (call.isSuccessful) {
                    books.postValue(booksResponseList)
                }
            }
        }
    }

    private fun loadAllHouses() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = utils.retrofit.create(ApiService::class.java).getHouses()
            val housesResponseList = call.body()

            run {
                if (call.isSuccessful) {
                    houses.postValue(housesResponseList)
                }
            }
        }
    }

    private fun loadAllCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = utils.retrofit.create(ApiService::class.java).getCharacters()
            val charactersResponseList = call.body()

            run {
                if (call.isSuccessful) {
                    characters.postValue(charactersResponseList)
                }
            }
        }
    }
}