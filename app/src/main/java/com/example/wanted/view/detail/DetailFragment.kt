package com.example.wanted.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.FragmentDetailBinding
import com.example.wanted.view.web.WebFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private var book: BookInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        with(viewModel) {

            previewLink.observe(viewLifecycleOwner) {
                val bundle = Bundle().apply {
                    putString("webUrlInfo", it)
                }

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, WebFragment().apply { arguments = bundle })
                    .addToBackStack(null)
                    .commit()
            }

            imageUrl.observe(viewLifecycleOwner) {
                val bundle = Bundle().apply {
                    putString("imageUrlInfo", it)
                }

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, DetailImageFragment().apply { arguments = bundle })
                    .addToBackStack(null)
                    .commit()
            }

            volumeInfo.observe(viewLifecycleOwner) {
                with(binding) {

                    Glide.with(requireContext())
                        .load(it?.imageLinks?.thumbnail)
                        .error(R.drawable.default_img)
                        .into(detailImage)

                    detailTitle.text = it?.title
                    detailWriterContent.text = it?.publisher ?: resources.getString(R.string.detail_empty)
                    detailPagesContent.text =
                        if (it?.pageCount == null) resources.getString(R.string.detail_empty) else resources.getString(
                            R.string.detail_pages_content,
                            it.pageCount
                        )
                    detailPublishDateContent.text = it?.publishedDate ?: resources.getString(R.string.detail_empty)
                    detailDescriptionContent.text = it?.description ?: resources.getString(R.string.detail_empty)
                }
            }
        }
    }

    private fun initListener() {

        with(binding) {
            val bookInfo = book?.volumeInfo

            viewModel.showBookInfo(bookInfo)

            detailPreview.setOnClickListener {
                viewModel.showPreviewPage(bookInfo?.previewLink)
            }

            detailImage.setOnClickListener {
                if(bookInfo?.imageLinks?.thumbnail != null) viewModel.showImage(bookInfo.imageLinks?.thumbnail)
            }
        }
    }
}