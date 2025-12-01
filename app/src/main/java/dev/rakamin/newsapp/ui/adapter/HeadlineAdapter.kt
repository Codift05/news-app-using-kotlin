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

class HeadlineAdapter(
    private var headlines: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>() {
    
    inner class HeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivHeadlineImage)
        val titleTextView: TextView = itemView.findViewById(R.id.tvHeadlineTitle)
        
        fun bind(article: Article) {
            titleTextView.text = article.title ?: "No Title"
            
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
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_headline, parent, false)
        return HeadlineViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.bind(headlines[position])
    }
    
    override fun getItemCount(): Int = headlines.size
    
    fun updateHeadlines(newHeadlines: List<Article>) {
        headlines = newHeadlines
        notifyDataSetChanged()
    }
}
