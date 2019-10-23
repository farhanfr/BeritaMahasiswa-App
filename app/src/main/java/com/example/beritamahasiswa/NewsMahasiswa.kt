package com.example.beritamahasiswa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beritamahasiswa.adapter.NewNewsAdapter
import com.example.beritamahasiswa.adapter.RegularNewsAdapter
import com.example.beritamahasiswa.adapter.TopNewsAdapter
import com.example.beritamahasiswa.retrofit.RetrofitServer
import com.example.beritamahasiswa.retrofit.api.ApiService
import com.example.beritamahasiswa.retrofit.response.ResponseNewsMahasiswa
import com.example.beritamahasiswa.stylelayout.GridItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsMahasiswa : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var TopNewsAdapter: TopNewsAdapter
    lateinit var NewNewsAdapter: NewNewsAdapter
    lateinit var RegularNewsAdapter: RegularNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_layout)

        //Top News Recyler View
        recyclerView = findViewById(R.id.rc_topNews)
        TopNewsAdapter = TopNewsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = TopNewsAdapter

        //New News RecylerView
        recyclerView = findViewById(R.id.rc_newNews)
        NewNewsAdapter = NewNewsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = NewNewsAdapter

        //Regular News RecylerView
        recyclerView = findViewById(R.id.rc_regularNews)
        RegularNewsAdapter = RegularNewsAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.addItemDecoration(GridItemDecoration(2,10,true))
        recyclerView.adapter = RegularNewsAdapter


        //Method Function
        get_TopNews()
        get_NewNews()
        get_regularNews()

    }

    private fun get_TopNews() {
        val apiService = RetrofitServer.buildService(ApiService::class.java)
        val requestCall = apiService.get_top_news()

        requestCall.enqueue(object : Callback<ResponseNewsMahasiswa>{
            override fun onResponse(call: Call<ResponseNewsMahasiswa>, response: Response<ResponseNewsMahasiswa>) {
                val responseStatus=response.body()!!.status
                if (responseStatus == 200){
                    TopNewsAdapter.setNewsListItems(response.body()!!.data)
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseNewsMahasiswa>, t: Throwable) {
                Toast.makeText(applicationContext,"maaf ada kesalahan dalam server kami",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    private fun get_NewNews() {
        var apiService = RetrofitServer.buildService(ApiService::class.java)
        var requestCall = apiService.get_new_news()

        requestCall.enqueue(object :Callback<ResponseNewsMahasiswa>{
            override fun onResponse(call: Call<ResponseNewsMahasiswa>, response: Response<ResponseNewsMahasiswa>) {
                val responseStatus = response.body()!!.status
                if (responseStatus == 200){
                    NewNewsAdapter.setNewNewsList(response.body()!!.data)
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseNewsMahasiswa>, t: Throwable) {
                Toast.makeText(applicationContext,"Maaf Ada kesalahan dalam server kami",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    private fun get_regularNews() {
        var apiService = RetrofitServer.buildService(ApiService::class.java)
        var requestCall = apiService.get_regular_news()
        requestCall.enqueue(object: Callback<ResponseNewsMahasiswa>{

            override fun onResponse(call: Call<ResponseNewsMahasiswa>, response: Response<ResponseNewsMahasiswa>) {
                var statusResponse = response.body()!!.status
                if (statusResponse == 200){
                    RegularNewsAdapter.setRegularNewsItem(response.body()!!.data)
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,response.body()!!.msg,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseNewsMahasiswa>, t: Throwable) {
                Toast.makeText(applicationContext,"Maaf Ada kesalahan dalam server kami",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}