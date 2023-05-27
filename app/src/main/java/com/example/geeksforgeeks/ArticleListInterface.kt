package com.example.geeksforgeeks

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val baseUrl = "https://api.rss2json.com"
interface ArticleListInterface {
    @GET("/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml")
    fun getData() : Call<Article>
}
object ArticleService{
    val articleInstance : ArticleListInterface
        init {
            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            articleInstance = retrofit.create(ArticleListInterface::class.java)
        }
}

//https://api.rss2json.com/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml