package com.emdp.pruebatecnica.mobgen.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories() : List<Categories>

    @Query("SELECT * FROM Categories WHERE type = :type")
    suspend fun getCategoryByType(type : Int) : Categories

    @Insert
    suspend fun insertCategory(category : Categories)

    @Insert
    suspend fun insertCategories(categoriesList : List<Categories>)
}