package com.example.demeg_flower.Home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demeg_flower.data.model.GArticle
import com.example.demeg_flower.databinding.ItemNewsBinding

class NewsAdapter(private val items: List<GArticle>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = items[position]
        holder.binding.tvNewsTitle.text = article.title
        holder.binding.tvNewsSource.text = article.source.name
        holder.binding.tvNewsDesc.text = article.description ?: "-"

        if (!article.image.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(article.image)
                .centerCrop()
                .into(holder.binding.imgNews)
        }
    }

    override fun getItemCount(): Int = items.size
}
