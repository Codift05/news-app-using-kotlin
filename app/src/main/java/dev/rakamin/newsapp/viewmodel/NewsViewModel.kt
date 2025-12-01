package dev.rakamin.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.rakamin.newsapp.model.Article
import dev.rakamin.newsapp.repository.NewsRepository
import dev.rakamin.newsapp.utils.Constants
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    
    private val repository = NewsRepository()
    
    // LiveData untuk headlines (horizontal RecyclerView)
    private val _headlines = MutableLiveData<List<Article>>()
    val headlines: LiveData<List<Article>> = _headlines
    
    // LiveData untuk news list (vertical RecyclerView)
    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> = _newsList
    
    // LiveData untuk loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    // LiveData untuk error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    // Pagination variables
    private var currentPage = Constants.INITIAL_PAGE
    private var totalResults = 0
    private var isLoadingMore = false
    
    // Current list untuk append new items
    private val currentNewsList = mutableListOf<Article>()
    
    init {
        getHeadlines()
        getNews(Constants.INITIAL_PAGE)
    }
    
    /**
     * Fetch top headlines for horizontal RecyclerView
     */
    fun getHeadlines() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getHeadlines(pageSize = 10)
                
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse ->
                        _headlines.value = newsResponse.articles ?: emptyList()
                    }
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Fetch news for vertical RecyclerView
     */
    fun getNews(page: Int = Constants.INITIAL_PAGE) {
        if (isLoadingMore) return
        
        viewModelScope.launch {
            try {
                isLoadingMore = true
                _isLoading.value = true
                
                val response = repository.getNews(
                    page = page,
                    pageSize = Constants.PAGE_SIZE
                )
                
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse ->
                        totalResults = newsResponse.totalResults ?: 0
                        
                        val articles = newsResponse.articles ?: emptyList()
                        
                        if (page == Constants.INITIAL_PAGE) {
                            // First load - replace all
                            currentNewsList.clear()
                            currentNewsList.addAll(articles)
                        } else {
                            // Load more - append
                            currentNewsList.addAll(articles)
                        }
                        
                        _newsList.value = currentNewsList.toList()
                        currentPage = page
                    }
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
                isLoadingMore = false
            }
        }
    }
    
    /**
     * Load more news for infinite scroll
     */
    fun loadMoreNews() {
        if (!isLoadingMore && currentNewsList.size < totalResults) {
            getNews(currentPage + 1)
        }
    }
    
    /**
     * Check if can load more
     */
    fun canLoadMore(): Boolean {
        return !isLoadingMore && currentNewsList.size < totalResults
    }
    
    /**
     * Refresh all data
     */
    fun refreshData() {
        currentPage = Constants.INITIAL_PAGE
        currentNewsList.clear()
        getHeadlines()
        getNews(Constants.INITIAL_PAGE)
    }
}
