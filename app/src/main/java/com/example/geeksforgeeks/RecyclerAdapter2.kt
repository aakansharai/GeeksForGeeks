package com.example.geeksforgeeks

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter2(val context : Context, val article: List<Item> = ArrayList()) : RecyclerView.Adapter<RecyclerAdapter2.ArticalViewHolder>(){


    private val ItemList : List<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticalViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.article_gfg, parent, false)
        return ArticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticalViewHolder, position: Int) {
        val article = article[position]

        holder.title.text = article.title
        holder.publishedAt.text = article.pubDate

        Glide.with(context).load(article.thumbnail)
                .placeholder(R.drawable.image)
                .into(holder.imageArticle)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Redirecting...", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ReadFullArticle::class.java)
            intent.putExtra("URL", article.link)
            context.startActivity(intent)
        }


//        Glide.with(context).load(artical.urlToImage)
//            .placeholder(R.drawable.image_not_found)
//            .into(holder.image)

//        holder.itemView.setOnClickListener {
//            Toast.makeText(context, "Redirecting...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, DetailedNews::class.java)
//            intent.putExtra("URL", artical.url)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return article.size
    }

    class ArticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.title)
        var imageArticle : ImageView = itemView.findViewById(R.id.imageView)
        var publishedAt : TextView = itemView.findViewById(R.id.date)

//        fun bind(data: Item , activity: Activity){
//            title.text = data.title
//            publishedAt.text = data.pubDate
//
//            Glide.with(itemView).load(data.thumbnail)
//                .placeholder(R.drawable.image)
//                .into(imageArticle)
//        }


    }

}