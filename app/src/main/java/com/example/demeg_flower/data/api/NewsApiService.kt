package com.example.demeg_flower.data.api

import com.example.demeg_flower.data.model.GNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("api/v4/top-headlines")
    suspend fun getTopHeadlines(
        @Query("lang") lang: String = "id",
        @Query("max") max: Int = 10,
        @Query("apikey") apiKey: String = "27626d32f3a1fd5c4b7ec69e2ff09e7e"
    ): GNewsResponse
}
