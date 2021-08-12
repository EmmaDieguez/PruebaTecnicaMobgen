package com.emdp.pruebatecnica.mobgen.model.api

import com.google.gson.annotations.SerializedName

class HousesResponse (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("region") var region : String,
    @SerializedName("title") var title : String
)