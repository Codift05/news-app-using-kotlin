package dev.rakamin.newsapp.utils

object Constants {
    // ⚠️ PENTING: Ganti dengan API Key Anda dari https://newsapi.org/
    const val API_KEY = "YOUR_API_KEY_HERE"
    
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
