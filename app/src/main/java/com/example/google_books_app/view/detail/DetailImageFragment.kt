package com.example.google_books_app.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.google_books_app.R
import com.example.google_books_app.databinding.FragmentDetailImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailImageFragment : Fragment() {

    private lateinit var binding: FragmentDetailImageBinding

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var imageUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_image, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setVariable(0, 0)
            lifecycleOwner = viewLifecycleOwner
        }

        imageUrl = arguments?.getString("imageUrlInfo") as String

        initLiveData()
        initListener()
    }

    private fun initLiveData() {
        viewModel.backIconOnClick.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initListener() {
        Glide.with(requireActivity())
            .load(imageUrl)
            .error(R.drawable.default_img)
            .into(binding.imageView)

        binding.detailImageBackIcon.setOnClickListener {
            viewModel.backIconClick()
        }
    }
}