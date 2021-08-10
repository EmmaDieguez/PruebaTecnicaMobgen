package com.emdp.pruebatecnica.mobgen.model.api

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

class BooksResponse (
    @SerializedName("name") var name : String,
    @SerializedName("isbn") var isbn : String,
    @SerializedName("authors") var authors : List<String>,
    @SerializedName("numberOfPages") var numberOfPages : Int,
    @SerializedName("publisher") var publisher : String,
    @SerializedName("country") var country : String,
    @SerializedName("mediaType") var mediaType : String,
    @SerializedName("released") var released : String
)