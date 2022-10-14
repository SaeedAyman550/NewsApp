package com.example.newsapp.ui

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(){

    lateinit var binding:ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val controller = getSupportFragmentManager()
            .findFragmentById(R.id.nav_host_fragment_containerfragment)?.findNavController()

        if (controller != null) {
            binding.bottomNavigationViewt.setupWithNavController(controller)
        }

    }


    fun goneBottomNav(){
        binding.bottomNavigationViewt.visibility=View.GONE

    }
    fun visibleBottomNav(){
        binding.bottomNavigationViewt.visibility=View.VISIBLE

    }



}
