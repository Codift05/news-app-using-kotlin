package dev.rakamin.newsapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.rakamin.newsapp.databinding.ActivityMainBinding
import dev.rakamin.newsapp.model.Article
import dev.rakamin.newsapp.ui.adapter.HeadlineAdapter
import dev.rakamin.newsapp.ui.adapter.NewsAdapter
import dev.rakamin.newsapp.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels()
    
    private lateinit var headlineAdapter: HeadlineAdapter
    private lateinit var newsAdapter: NewsAdapter
    
    private var isLoading = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupRecyclerViews()
        setupObservers()
        setupInfiniteScroll()
        setupSwipeRefresh()
    }
    
    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "News App"
    }
    
    private fun setupRecyclerViews() {
        // Setup horizontal RecyclerView for headlines
        headlineAdapter = HeadlineAdapter(emptyList()) { article ->
            openDetailActivity(article)
        }
        
        binding.rvHeadlines.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = headlineAdapter
            setHasFixedSize(true)
        }
        
        // Setup vertical RecyclerView for news list
        newsAdapter = NewsAdapter(emptyList()) { article ->
            openDetailActivity(article)
        }
        
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            setHasFixedSize(true)
        }
    }
    
    private fun setupObservers() {
        // Observe headlines
        viewModel.headlines.observe(this) { headlines ->
            headlineAdapter.updateHeadlines(headlines)
        }
        
        // Observe news list
        viewModel.newsList.observe(this) { newsList ->
            newsAdapter.updateNews(newsList)
        }
        
        // Observe loading state
        viewModel.isLoading.observe(this) { loading ->
            isLoading = loading
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            binding.swipeRefresh.isRefreshing = loading
        }
        
        // Observe error messages
        viewModel.errorMessage.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupInfiniteScroll() {
        binding.rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                
                // Load more when 80% scrolled
                if (!isLoading && viewModel.canLoadMore()) {
                    if (lastVisibleItem >= (totalItemCount * 0.8).toInt()) {
                        viewModel.loadMoreNews()
                    }
                }
            }
        })
    }
    
    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
    }
    
    private fun openDetailActivity(article: Article) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_ARTICLE, article)
        }
        startActivity(intent)
    }
}
