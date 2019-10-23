package com.example.beritamahasiswa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamahasiswa.R
import com.example.beritamahasiswa.retrofit.model.NewsModel

class NewNewsAdapter(val context: Context) : RecyclerView.Adapter<NewNewsAdapter.ViewHolder>() {

    var ListNewModel: List<NewsModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewNewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_newnews_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ListNewModel.size
    }

    override fun onBindViewHolder(holder: NewNewsAdapter.ViewHolder, position: Int) {
        holder.tv_titleNews.text = ListNewModel.get(position).news_title

    }

    fun setNewNewsList(ListNewModel:List<NewsModel>){
        this.ListNewModel=ListNewModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_titleNews:TextView=itemView.findViewById(R.id.tv_categoryNews)
    }


}