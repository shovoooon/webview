package com.bdjobs.today.link

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import com.bdjobs.today.R
import com.bdjobs.today.utill.DetectConnection

class LinkFragment : Fragment() {

    private val TAG = "LinkFragment"
    private lateinit var webView: WebView
    private var URL = ""
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_link, container, false)

        if (arguments != null && arguments?.getString("link") != null) {
            Log.v(TAG, "Notification: ${arguments?.getString("link")}")

        }

        progressBar = view.findViewById(R.id.progressBar)
        webView = view.findViewById(R.id.wv)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                //title = "Loading..."
                Log.v("Progress", progress.toString())
                //setProgress(progress * 100) //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                //if (progress == 100) setTitle(R.string.app_name)
                if (progress < 75) {
                    progressBar.visibility = View.VISIBLE
                } else progressBar.visibility = View.GONE
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (DetectConnection.checkInternetConnection(requireActivity())) {
                    view!!.loadUrl(url!!)
                } else {
                    Toast.makeText(requireActivity(), "No Internet Connection", Toast.LENGTH_LONG)
                        .show()
                }

                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        //webView.canGoBack()
        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK
                    && event!!.action == MotionEvent.ACTION_UP
                    && webView.canGoBack()
                ) {
                    webView.goBack()
                    return true
                }
                return false
            }

        })
        URL = arguments?.getString("link")!!
        webView.loadUrl(URL)

        return view
    }

}