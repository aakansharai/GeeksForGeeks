package com.example.geeksforgeeks

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast

class ReadFullArticle : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_full_article)

        val url = intent.getStringExtra("URL")
        var article : WebView = findViewById(R.id.viewArticle)
        var progressbar : ProgressBar = findViewById(R.id.progressBarArticle)

        progressbar.visibility = View.VISIBLE

        if(url != null){
            article.settings.javaScriptEnabled = true
            article.settings.userAgentString = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"

            article.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressbar.visibility= View.GONE
                    article.visibility = View.VISIBLE
                }
            }
            article.loadUrl(url)
        }
        else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            progressbar.visibility = View.GONE
        }

    }
}