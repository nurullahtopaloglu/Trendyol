package com.example.trendyol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.trendyol.R
import com.example.trendyol.databinding.ActivityMainBinding
import com.example.trendyol.ui.util.ToolbarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarHandler {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setToolbarView(this, binding.toolbar)

        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "Something goes wrong! Try again later..", Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    fun setTitle(title: String) {
        setTitle(binding.toolbar, title)
    }

    fun isBackVisible(isVisible: Boolean) {
        setBackVisibility(binding.toolbar, isVisible)
    }

    fun isCloseVisible(isVisible: Boolean) {
        setCloseVisibility(binding.toolbar, isVisible)
    }
}