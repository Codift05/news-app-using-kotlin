package dev.rakamin.newsapp.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import dev.rakamin.newsapp.R
import dev.rakamin.newsapp.model.Article
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    
    private var article: Article? = null

    // Views
    private lateinit var toolbar: Toolbar
    private lateinit var ivDetailImage: ImageView
    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailSource: TextView
    private lateinit var tvDetailAuthor: TextView
    private lateinit var tvDetailContent: TextView
    private lateinit var btnOpenBrowser: Button
    
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // find views by id
        toolbar = findViewById(R.id.toolbar)
        ivDetailImage = findViewById(R.id.ivDetailImage)
        tvDetailTitle = findViewById(R.id.tvDetailTitle)
        tvDetailSource = findViewById(R.id.tvDetailSource)
        tvDetailAuthor = findViewById(R.id.tvDetailAuthor)
        tvDetailContent = findViewById(R.id.tvDetailContent)
        btnOpenBrowser = findViewById(R.id.btnOpenBrowser)
        
        article = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ARTICLE, Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARTICLE)
        }
        
        setupUI()
        displayArticle()
    }
    
    private fun setupUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Detail Berita"
        }
        
        toolbar.setNavigationOnClickListener {
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
                .into(ivDetailImage)
            
            // Set title
            tvDetailTitle.text = article.title ?: "No Title"
            
            // Set source and date
            val source = article.source?.name ?: "Unknown Source"
            val date = formatDate(article.publishedAt)
            tvDetailSource.text = "$source • $date"
            
            // Set author
            tvDetailAuthor.text = if (!article.author.isNullOrEmpty()) {
                "By ${article.author}"
            } else {
                "Author Unknown"
            }
            
            // Set content
            val content = article.content ?: article.description ?: "No content available"
            tvDetailContent.text = content
            
            // Setup button to open in browser
            btnOpenBrowser.setOnClickListener {
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
