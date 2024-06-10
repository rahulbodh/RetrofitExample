package com.example.learnretrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSpecificAlbum(@Query("id") id: Int): Response<Albums>

    @POST("/albums")
    suspend fun addAlbum(@Body album: AlbumItem): Response<AlbumItem>

}