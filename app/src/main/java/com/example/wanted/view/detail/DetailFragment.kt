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

    private var book: BookInfo? = null

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

        book = arguments?.getParcelable("bookInfo") as BookInfo?

        initLiveData()
        initListener()
    }

    private fun initLiveData() {

    }

    private fun initListener() {

        with(binding) {

            val bookInfo = book?.volumeInfo

            Glide.with(requireContext())
                .load(bookInfo?.imageLinks?.thumbnail)
                .into(detailImage)

            detailTitle.text = bookInfo?.title
            detailWriterContent.text = bookInfo?.publisher ?: resources.getString(R.string.detail_empty)
            detailPagesContent.text = if(bookInfo?.pageCount == null) resources.getString(R.string.detail_empty) else resources.getString(R.string.detail_pages_content, bookInfo?.pageCount)

        }
    }
}