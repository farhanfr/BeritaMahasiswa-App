package com.example.beritamahasiswa.retrofit.response

import com.example.beritamahasiswa.retrofit.model.NewsModel

data class ResponseNewsMahasiswa ( val status:Int, val data:List<NewsModel>, val msg:String)