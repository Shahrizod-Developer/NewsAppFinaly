package uz.gita.newsappfinaly.data.remote.response

import uz.gita.newsappfinaly.data.model.Article

data class BaseResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)