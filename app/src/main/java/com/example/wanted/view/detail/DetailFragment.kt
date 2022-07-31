package com.example.wanted.view.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()

    private var bookInfo: BookInfo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setVariable(0, viewModel)
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        bookInfo = arguments?.getParcelable("bookInfo") as BookInfo?

        initLiveData()
        initListener()
    }

    private fun initLiveData() {

    }

    private fun initListener() {

        Glide.with(requireContext())
            .load(bookInfo?.volumeInfo?.imageLinks?.thumbnail)
            .into(binding.detailImage)

        binding.detailTitle.text = bookInfo?.volumeInfo?.title

    }
}