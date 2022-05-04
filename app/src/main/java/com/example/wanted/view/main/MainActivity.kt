package com.example.wanted.view.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted.R
import com.example.wanted.databinding.ActivityMainBinding
import com.example.wanted.view.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("LogNotTimber")
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var index = 0

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(0, viewModel)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        initLiveData()
        initListener()

        setBookRecyclerView()
        setRecyclerViewScrollListener()
    }

    private fun initLiveData() {

        viewModel.bookList.observe(this) {
            binding.apply {

                adapter.submitList(it.items?.toList())
                searchedItemNum.text = "검색된 도서 수 : ${it.totalItems}"
            }
        }
    }

    private fun initListener() {
        binding.apply {
            searchBtn.setOnClickListener {
                viewModel.getSearchedBookList(searchEt.text.toString(), 0)
            }
        }
    }

    private fun setBookRecyclerView() {
        adapter = MainAdapter()
        binding.bookRecyclerview.adapter = adapter
    }

    private fun setRecyclerViewScrollListener(){
        binding.apply {
            bookRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount-1

                    if(!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                        index+=40
                        viewModel.getSearchedBookList(searchEt.text.toString(), index)
                        Log.d("test:", index.toString())
                    }
                }
            })
        }
    }

}