package com.emdp.pruebatecnica.mobgen.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import com.emdp.pruebatecnica.mobgen.model.database.CategoriesDao

@Database(
    entities = [Categories::class],
    version = 1
)
abstract class GameOfThronesDb : RoomDatabase() {
    abstract fun categoriesDao() : CategoriesDao

    companion object {
        private var INSTANCE : GameOfThronesDb? = null

        fun getAppDatabase(context : Context) : GameOfThronesDb? {
            if (null == INSTANCE) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext,
                        GameOfThronesDb::class.java, "GotDb")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}