package com.example.wanted.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(0, viewModel)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.getAllBooks()

        initLiveData()
        initListener()



    }

    @SuppressLint("LogNotTimber")
    private fun initLiveData() {
        viewModel.allBooks.observe(this) {
            binding.apply {

            }
        }
    }

    private fun initListener() {
        binding.apply {

        }
    }


}