package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.models.Article
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private lateinit var onItemClickListener: (Article) -> Unit

    private var articleList = mutableListOf<Article>()

      inner class ArticleViewHolder(itemview:View): RecyclerView.ViewHolder(itemview){

       var bind: ItemArticleBinding
        init {
             bind= ItemArticleBinding.bind(itemview)

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article,parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var article= articleList?.get(position)
        holder.bind.apply {

            if (article != null) {
                Glide.with(this.root).load(article.urlToImage).into(itemImage)
                tvTitle.text = article.title
                tvSource.text=article.source?.name
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt


                root.setOnClickListener {
                    onItemClickListener(article)


                }
            }


        }

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun getArticle(position: Int): Article {
        return articleList.get(position)

    }

    fun submitList(list: List<Article>){
        if (list != null) {
            articleList = list as MutableList<Article>
        }
       notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


}