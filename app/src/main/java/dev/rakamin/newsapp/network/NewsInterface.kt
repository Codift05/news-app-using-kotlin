package dev.rakamin.newsapp.network

import dev.rakamin.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    
    /**
     * Get top headlines from Indonesia
     * Endpoint: /v2/top-headlines
     */
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Response<NewsResponse>
    
    /**
     * Search for news articles
     * Endpoint: /v2/everything
     */
    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("sortBy") sortBy: String = "publishedAt"
    ): Response<NewsResponse>
}
