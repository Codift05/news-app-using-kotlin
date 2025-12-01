package dev.rakamin.newsapp.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.rakamin.newsapp.R
import dev.rakamin.newsapp.databinding.ActivityDetailBinding
import dev.rakamin.newsapp.model.Article
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailBinding
    private var article: Article? = null
    
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        article = intent.getParcelableExtra(EXTRA_ARTICLE)
        
        setupUI()
        displayArticle()
    }
    
    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Detail Berita"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    
    private fun displayArticle() {
        article?.let { article ->
            // Load image
            Glide.with(this)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.ivDetailImage)
            
            // Set title
            binding.tvDetailTitle.text = article.title ?: "No Title"
            
            // Set source and date
            val source = article.source?.name ?: "Unknown Source"
            val date = formatDate(article.publishedAt)
            binding.tvDetailSource.text = "$source • $date"
            
            // Set author
            binding.tvDetailAuthor.text = if (!article.author.isNullOrEmpty()) {
                "By ${article.author}"
            } else {
                "Author Unknown"
            }
            
            // Set content
            val content = article.content ?: article.description ?: "No content available"
            binding.tvDetailContent.text = content
            
            // Setup button to open in browser
            binding.btnOpenBrowser.setOnClickListener {
                article.url?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }
        }
    }
    
    private fun formatDate(dateString: String?): String {
        if (dateString.isNullOrEmpty()) return "Unknown Date"
        
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(dateString)
            
            val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
            date?.let { outputFormat.format(it) } ?: "Unknown Date"
        } catch (e: Exception) {
            "Unknown Date"
        }
    }
}
