package com.emdp.pruebatecnica.mobgen.model.api

import com.google.gson.annotations.SerializedName

data class CategoriesResponse (
    @SerializedName("category_name") var categoryName : String,
    @SerializedName("type") var type : Int
    )