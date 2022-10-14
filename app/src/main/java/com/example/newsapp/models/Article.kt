package com.example.newsapp.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Entity(tableName = "article_table")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val author: String?,
    val content: String?,
    val description:String?,
    val publishedAt:String?,
    val source: Source?,
    val title:String?,
    val url:String?,
    val urlToImage:String?

): Parcelable {


    override fun hashCode(): Int {
        return super.hashCode()
    }

    companion object : Parceler<Article> {

        override fun Article.write(p0: Parcel, p1: Int) {
        }

        override fun create(parcel: Parcel): Article = TODO()
    }

}

