package dev.rakamin.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.rakamin.newsapp.R
import dev.rakamin.newsapp.model.Article
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private var newsList: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    
    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivNewsImage)
        val titleTextView: TextView = itemView.findViewById(R.id.tvNewsTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvNewsDescription)
        val sourceTextView: TextView = itemView.findViewById(R.id.tvNewsSource)
        val dateTextView: TextView = itemView.findViewById(R.id.tvNewsDate)
        
        fun bind(article: Article) {
            titleTextView.text = article.title ?: "No Title"
            descriptionTextView.text = article.description ?: "No Description"
            sourceTextView.text = article.source?.name ?: "Unknown Source"
            
            // Format date
            dateTextView.text = formatDate(article.publishedAt)
            
            // Load image
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .centerCrop()
                .into(imageView)
            
            itemView.setOnClickListener {
                onItemClick(article)
            }
        }
        
        private fun formatDate(dateString: String?): String {
            if (dateString.isNullOrEmpty()) return "Unknown Date"
            
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = inputFormat.parse(dateString)
                
                val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                date?.let { outputFormat.format(it) } ?: "Unknown Date"
            } catch (e: Exception) {
                "Unknown Date"
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }
    
    override fun getItemCount(): Int = newsList.size
    
    fun updateNews(newNewsList: List<Article>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }
}
