package dev.rakamin.newsapp.utils

object Constants {
    // API Key from newsapi.org
    const val API_KEY = "fa3a17eafe0541febac54a3d7f15c0ba"
    
    const val BASE_URL = "https://newsapi.org/"
    const val PAGE_SIZE = 20
    const val INITIAL_PAGE = 1
    const val COUNTRY_ID = "id"
    
    // Query default untuk everything endpoint
    const val DEFAULT_QUERY = "Indonesia"
    
    // Timeout settings
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}
