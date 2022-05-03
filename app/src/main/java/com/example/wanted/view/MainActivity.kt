package com.example.wanted.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.getAllBooks()

        initLiveData()
        initListener()



    }

    private fun initLiveData() {
        viewModel.allBooks.observe(this) {
            binding.apply {

            }
        }

    }

    private fun initListener() {

    }


}