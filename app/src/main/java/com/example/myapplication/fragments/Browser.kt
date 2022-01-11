package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.BrowserBinding

class Browser : Fragment() {

    private var _binding: BrowserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BrowserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = binding.webView

        //webView settings
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        //load google by default
        webView.loadUrl("https://www.google.com/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}