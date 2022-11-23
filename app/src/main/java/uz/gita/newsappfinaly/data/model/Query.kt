package uz.gita.newsappfinaly.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val country: String,
    val category: String,
    val source: String,
) : Parcelable