package com.example.newsapp.ui.fragments.search

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Resourse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    @Inject
    lateinit var adapter : NewsAdapter
    lateinit var binding : FragmentSearchBinding
    var job:Job?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentSearchBinding.bind(view)

        (requireActivity() as NewsActivity).visibleBottomNav()
        val viewModel:SearchViewModel=ViewModelProvider(this).get(SearchViewModel::class.java)
        adapter.setOnItemClickListener {

            var dir= SearchFragmentDirections.actionSearchFragmentToArticleFragment(it,true)
            Navigation.findNavController(binding.root).navigate(dir)


        }




        binding.etSearch.addTextChangedListener{

            job?.cancel()
            job= lifecycleScope.launch {

                delay(Constants.SEARCH_NEWS_TIME_DELAY)
                it?.let {

                    if (it.toString().isNotEmpty()) {
                        viewModel.searchNews(it.toString())
                    }
                }
            }
            if(it.toString().isEmpty())
                binding.rvSearchNews.adapter=null

        }


        viewModel.searchNewsLiveData.observe(viewLifecycleOwner){

            when (it) {
                is Resourse.Loading ->
                    binding.searchProgressBar.visibility = View.VISIBLE


                is Resourse.Success -> {
                    binding.searchProgressBar.visibility = View.GONE
                    binding.rvSearchNews.adapter=adapter
                    it.data?.articles.let {
                        if (it != null)
                            adapter.submitList(it)
                    }

                }

                is Resourse.Error -> {
                    binding.searchProgressBar.visibility = View.GONE
                    Toast.makeText(context, "${it.message} error", Toast.LENGTH_SHORT).show()
                }




            }





        }


    }


}