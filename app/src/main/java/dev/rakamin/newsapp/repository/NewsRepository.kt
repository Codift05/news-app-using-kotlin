package dev.rakamin.newsapp.repository

import dev.rakamin.newsapp.model.NewsResponse
import dev.rakamin.newsapp.network.NewsService
import dev.rakamin.newsapp.utils.Constants
import retrofit2.Response

class NewsRepository {
    
    private val api = NewsService.api
    
    /**
     * Fetch top headlines from Indonesia
     */
    suspend fun getHeadlines(
        country: String = Constants.COUNTRY_ID,
        page: Int = Constants.INITIAL_PAGE,
        pageSize: Int = 10
    ): Response<NewsResponse> {
        return api.getHeadlines(
            country = country,
            apiKey = Constants.API_KEY,
            page = page,
            pageSize = pageSize
        )
    }
    
    /**
     * Fetch all news articles with query
     */
    suspend fun getNews(
        query: String = Constants.DEFAULT_QUERY,
        page: Int = Constants.INITIAL_PAGE,
        pageSize: Int = Constants.PAGE_SIZE
    ): Response<NewsResponse> {
        return api.getEverything(
            query = query,
            apiKey = Constants.API_KEY,
            page = page,
            pageSize = pageSize
        )
    }
}
