package com.example.wanted.view.web

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.wanted.R
import com.example.wanted.data.domain.BookInfo
import com.example.wanted.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebFragment : Fragment() {

    private lateinit var binding : FragmentWebBinding

    private lateinit var urlInfo: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setVariable(0, 0)
            lifecycleOwner = viewLifecycleOwner

            webView.apply{
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true

                urlInfo = arguments?.getString("urlInfo") as String

                loadUrl(urlInfo)
            }
        }
    }
}