package com.example.demeg_flower.data.model

data class GNewsResponse(
    val totalArticles: Int,
    val articles: List<GArticle>
)

data class GArticle(
    val title: String,
    val description: String?,
    val content: String?,
    val url: String,
    val image: String?,
    val publishedAt: String,
    val source: GSource
)

data class GSource(
    val name: String,
    val url: String
)
