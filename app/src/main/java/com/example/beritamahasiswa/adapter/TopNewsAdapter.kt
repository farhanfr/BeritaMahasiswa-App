package com.example.beritamahasiswa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamahasiswa.R
import com.example.beritamahasiswa.retrofit.model.NewsModel

class TopNewsAdapter(val context: Context): RecyclerView.Adapter<TopNewsAdapter.ViewHolder>(){
    var ListNewsModel : List<NewsModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_topnews_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ListNewsModel.size
    }

    override fun onBindViewHolder(holder: TopNewsAdapter.ViewHolder, position: Int) {
        holder.tv_titleNews.text = ListNewsModel.get(position).news_title
//        holder.cv_topNews.radius.compareTo(30)
    }

    fun setNewsListItems(ListNewsModel: List<NewsModel>){
        this.ListNewsModel = ListNewsModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tv_titleNews:TextView = itemView.findViewById(R.id.tv_categoryNews)
//            val cv_topNews:CardView = itemView.findViewById(R.id.cv_newNews)
    }

}


