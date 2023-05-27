package com.example.geeksforgeeks

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter : RecyclerAdapter
    lateinit var reload : SwipeRefreshLayout
    lateinit var recyclerArticle : RecyclerView

    lateinit var cardL : CardView
    lateinit var largeTitle : TextView
    lateinit var date : TextView
    lateinit var imageLarge : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getArticle()

//        On Swipe
        reload = findViewById(R.id.swipeReload)
        reload.setOnRefreshListener {
            getArticle()
            reload.isRefreshing = false
        }
    }

    private fun getArticle() {
        val recycler : RecyclerView = findViewById(R.id.recyclerContainer)
        val progressBar : ProgressBar = findViewById(R.id.progress)

//        BIG TOP ARTICLE
        cardL = findViewById(R.id.LargeArticle)
        largeTitle = findViewById(R.id.titleLargeArticle)
        date = findViewById(R.id.date)
        imageLarge = findViewById(R.id.imageViewLargeArticle)

        recycler.visibility = View.GONE
        cardL.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val art = ArticleService.articleInstance.getData()
        art.enqueue(object : retrofit2.Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                val news = response.body()
                Log.d("AAKA", "response get")
                if (news != null){

                    //==========  Article list - Adapter  ==============
                    adapter = RecyclerAdapter(this@MainActivity, news.items)
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)


                    //============ Adding data to Large- Top article, First set of News Data from the list ==========
                    largeTitle.text = news.items[1].title
                    date.text = news.items[1].pubDate
                    Glide.with(this@MainActivity).load(news.items[1].thumbnail)
                        .placeholder(R.drawable.image)
                        .into(imageLarge)


                    //============  TO read the entire Article   ============================
                    cardL.setOnClickListener {
                        Toast.makeText(this@MainActivity, "Redirecting...", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, ReadFullArticle::class.java)
                        intent.putExtra("URL", news.items[1].link)
                        startActivity(intent)
                    }

                    //================  UI Changes  ==================
                    progressBar.visibility = View.GONE
                    cardL.visibility = View.VISIBLE
                    recycler.visibility = View.VISIBLE
                }
                else{
                    Log.d("AAKA", "unexpected fetching news Error")
                    progressBar.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.d("AAKA", "Error in fetching news",t)
                progressBar.visibility = View.GONE}
        })
    }


}