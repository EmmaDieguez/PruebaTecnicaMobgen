package com.emdp.pruebatecnica.mobgen.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Categories(
    val categoryName : String,
    val type : Int
) : Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}