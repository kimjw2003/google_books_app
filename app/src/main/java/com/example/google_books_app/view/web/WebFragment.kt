package com.example.google_books_app.view.web

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.google_books_app.R
import com.example.google_books_app.databinding.FragmentWebBinding
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

                urlInfo = arguments?.getString("webUrlInfo") as String

                loadUrl(urlInfo)

                setOnKeyListener(View.OnKeyListener{ _, keyCode, _ ->
                    if(keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
                        goBack()
                        return@OnKeyListener true
                    }

                    return@OnKeyListener false
                })
            }
        }
    }


}