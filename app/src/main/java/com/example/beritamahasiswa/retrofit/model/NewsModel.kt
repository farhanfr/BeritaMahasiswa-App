package com.example.beritamahasiswa.retrofit.model

data class NewsModel (
    var news_id:Int? = null,
    var category_news_id:Int? = null,
    var news_title:String? = null,
    var news_content:String? = null,
    var news_img:String? = null,
    var date_published:String? = null,
    var news_tipe:String?=null
)