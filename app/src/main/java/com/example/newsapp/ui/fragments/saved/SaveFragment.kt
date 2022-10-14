package com.example.newsapp.ui.fragments.saved

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentSaveBinding
import com.example.newsapp.ui.NewsActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class SaveFragment : Fragment(R.layout.fragment_save) {

    lateinit var binding: FragmentSaveBinding
    @Inject
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentSaveBinding.bind(view)

        (requireActivity() as NewsActivity).visibleBottomNav()

        val viewmodel: SavedViewModel = ViewModelProvider(this).get(SavedViewModel::class.java)

        binding.rvSavedNews.adapter = adapter
        adapter.setOnItemClickListener {

            Navigation.findNavController(binding.root).navigate(SaveFragmentDirections.actionSavedFragmentToArticleFragment(it,false))
        }


        viewmodel.getSavedNews().observe(viewLifecycleOwner, Observer {


            adapter.submitList(it)





        })


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapter.getArticle(position)
                viewmodel.deleteArticle(article)
                Snackbar.make(binding.root, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewmodel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }
    }



}
