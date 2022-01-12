package com.example.myapplication.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.BrowserBinding
import androidx.core.content.ContextCompat.getSystemService




class Browser : Fragment() {

    private var _binding: BrowserBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView
    private lateinit var url: String

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
        webView = binding.webView

        //webView settings
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        //load google by default
        webView.loadUrl("https://www.google.com/")

        //for menu
        registerForContextMenu(webView)

        //onBackButtonPressed
        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    goBack()
                }
                return true
            }
        })


    }

    //go to previous page
    fun goBack() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            activity?.let {
                it.onBackPressed()
            }
        }
    }

    //menu items
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu.add(0, 0, 0 ,"Copy Link")
        menu.add(0, 1, 0, "Download")

        //save the url
        val webView = v as WebView
        url = webView.hitTestResult.extra.toString()
    }

    //menu items click listener
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //copy url to clipboard
            0 -> {
                val clipboardManager = getSystemService(requireContext(), ClipboardManager::class.java)
                val clipData = ClipData.newPlainText("url", url)
                clipboardManager?.let {
                    clipboardManager.setPrimaryClip(clipData)
                }
            }
            //download the file
            1 -> {

            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}