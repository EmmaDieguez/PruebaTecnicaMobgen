package com.emdp.pruebatecnica.mobgen.model.api

import com.google.gson.annotations.SerializedName

class CharactersResponse (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("gender") var gender : String,
    @SerializedName("culture") var culture : String,
    @SerializedName("born") var born : String,
    @SerializedName("died") var died : String,
    @SerializedName("titles") var titles : List<String> = arrayListOf(),
    @SerializedName("aliases") var aliases : List<String> = arrayListOf(),
    @SerializedName("father") var father : String,
    @SerializedName("mother") var mother : String,
    @SerializedName("spouse") var spouse : String,
    @SerializedName("allegiances") var allegiances : List<String> = arrayListOf(),
    @SerializedName("playedBy") var playedBy : List<String> = arrayListOf()
)