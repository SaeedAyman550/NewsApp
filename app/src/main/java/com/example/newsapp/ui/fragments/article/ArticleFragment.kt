package com.example.newsapp.ui.fragments.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.*
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.models.Article
import com.example.newsapp.ui.NewsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {


    lateinit var binding:FragmentArticleBinding

    lateinit var article: Article
     var showFloatingActionButton:Boolean?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var art=ArticleFragmentArgs.fromBundle(requireArguments()).article
        var showButton=ArticleFragmentArgs.fromBundle(requireArguments()).showFloatingActionButton
        if (art!=null&&showButton!=null) {
            article = art
            showFloatingActionButton = showButton
        }
       ( requireActivity()as NewsActivity).goneBottomNav()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentArticleBinding.bind(view)
        val viewModel:ArticleViewModel=ViewModelProvider(this).get(ArticleViewModel::class.java)



        if (showFloatingActionButton==true)
            binding.fab.visibility=View.VISIBLE
        else
            binding.fab.visibility=View.GONE


        binding.fab.setOnClickListener{


            viewModel.saveArticle(article)


        }


        binding.webView.apply {
            webViewClient=WebViewClient()
            article.url?.let { loadUrl(it) }
        }

    }
}