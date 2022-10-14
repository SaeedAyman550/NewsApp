package com.example.newsapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.Article
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.util.Resourse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var adapter: NewsAdapter

    lateinit var binding: FragmentHomeBinding
    lateinit var viewmodel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        binding.rvHomeNews.adapter = adapter
        adapter.setOnItemClickListener {
          findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToArticleFragment(it, true))

        }
        (requireActivity() as NewsActivity).visibleBottomNav()

        viewmodel= ViewModelProvider(this).get(HomeViewModel::class.java)
        viewmodel.getAllNews("eg")

        viewmodel.homeNewsLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is Resourse.Loading ->
                    binding.HomeProgressBar.visibility = View.VISIBLE


                is Resourse.Success -> {
                    binding.HomeProgressBar.visibility = View.GONE
                    it.data?.articles?.let {

                        if (it != null)
                            adapter.submitList(it)

                    }
                }
                is Resourse.Error -> {
                    binding.HomeProgressBar.visibility = View.GONE
                    Toast.makeText(context, "${it.message} error", Toast.LENGTH_SHORT).show()
                }



            }




        }

    }





}