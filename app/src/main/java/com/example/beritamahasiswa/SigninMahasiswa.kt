package com.example.beritamahasiswa

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beritamahasiswa.preferences.SharedPreference
import com.example.beritamahasiswa.retrofit.RetrofitServer
import com.example.beritamahasiswa.retrofit.api.ApiService
import com.example.beritamahasiswa.retrofit.response.ResponseSigninMahasiswa
import kotlinx.android.synthetic.main.signin_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninMahasiswa : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_layout)

        et_toSignuplayout.setOnClickListener {
            startActivity(Intent(this,SignupMahasiswa::class.java))
        }

        val sharedPreference: SharedPreference = SharedPreference(this)
        if (sharedPreference.getValueBoolean("status")){
            startActivity(Intent(this,HomeMahasiswa::class.java))
            finish()
        }

        btn_signin.setOnClickListener {
            val mahasiswaNim = et_mahasiswaNim.text.toString().trim()
            val mahasiswaPassword = et_mahasiswaPassword.text.toString().trim()
            login_mahasiswa(mahasiswaNim,mahasiswaPassword)
        }

    }

    private fun login_mahasiswa(mahasiswaNim: String, mahasiswaPassword: String) {
        val progressdialog = ProgressDialog(this)
        progressdialog.setMessage("Login ....")
        progressdialog.setCancelable(false)
        progressdialog.show()

        val sharedPreference:SharedPreference=SharedPreference(this)
        val apiService = RetrofitServer.buildService(ApiService::class.java)
        val requestCall = apiService.login_mahasiswa(mahasiswaNim,mahasiswaPassword)

        requestCall.enqueue(object :Callback<ResponseSigninMahasiswa>{
            override fun onResponse(call: Call<ResponseSigninMahasiswa>, response: Response<ResponseSigninMahasiswa>) {
                var responseStatus = response.body()!!.status
                var responseData = response.body()!!.data
                if (responseStatus == 200){
                    Log.d("Server",response.body()!!.msg)
                    responseData.forEach {
                        sharedPreference.save("mahasiswa_id",it.mahasiswa_id!!)
                        sharedPreference.save("mahasiswa_nim",it.mahasiswa_nim!!)
                        sharedPreference.save("mahasiswa_name",it.mahasiswa_name!!)
                        sharedPreference.save("status",true)
                    }
                    startActivity(Intent(this@SigninMahasiswa,HomeMahasiswa::class.java))
                    finish()
                    Toast.makeText(applicationContext,"Berhasil Login",Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d("Server",response.body()!!.msg)
                    Toast.makeText(applicationContext,"Username atau password salah",Toast.LENGTH_SHORT).show()
                    progressdialog.hide()
                }
            }

            override fun onFailure(call: Call<ResponseSigninMahasiswa>, t: Throwable) {
                Toast.makeText(applicationContext,"Maaf server kami dalam masalah",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}