package com.example.learnretrofit

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    @SerializedName("userId")
    private val userId : Int,

    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title : String
)
