package com.example.beritamahasiswa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beritamahasiswa.retrofit.RetrofitServer
import com.example.beritamahasiswa.retrofit.api.ApiService
import com.example.beritamahasiswa.retrofit.response.ResponseSignupMahasiswa
import kotlinx.android.synthetic.main.signup_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupMahasiswa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)

        et_toSigninlayout.setOnClickListener {
            startActivity(Intent(this,SigninMahasiswa::class.java))
        }

        btn_register.setOnClickListener {
            val mahasiswaName = et_mahasiswaName.text.toString().trim()
            val mahasiswaNim = et_mahasiswaNim.text.toString().trim()
            val mahasiswaClass = et_mahasiswaClass.text.toString().trim()
            val mahasiswaBorn = et_mahasiswaBorn.text.toString().trim()
            val mahasiswaPassword = et_mahasiswaPassword.text.toString().trim()

            registerMahasiswa(mahasiswaName,mahasiswaNim,mahasiswaClass,mahasiswaBorn,mahasiswaPassword);
        }
    }

    private fun registerMahasiswa(mahasiswaName: String, mahasiswaNim: String, mahasiswaClass: String, mahasiswaBorn: String, mahasiswaPassword: String) {

        val apiService = RetrofitServer.buildService(ApiService::class.java)
        val requestCall = apiService.add_mahasiswa(mahasiswaName,mahasiswaNim,mahasiswaClass,mahasiswaBorn,mahasiswaPassword)

        requestCall.enqueue(object : Callback<ResponseSignupMahasiswa>{
            override fun onResponse(call: Call<ResponseSignupMahasiswa>, response: Response<ResponseSignupMahasiswa>){
                var responseStatus = response.body()!!.status
                if (responseStatus == 200){
                    et_mahasiswaName.text.clear()
                    et_mahasiswaNim.text.clear()
                    et_mahasiswaClass.text.clear()
                    et_mahasiswaBorn.text.clear()
                    et_mahasiswaPassword.text.clear()
                    Log.d("Server",response.message())
                    Toast.makeText(applicationContext,"Berhasil Daftar",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignupMahasiswa,SigninMahasiswa::class.java))

                }
                else{
                    Log.d("Server",response.message())
                    Toast.makeText(applicationContext,"Gagal Daftar",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ResponseSignupMahasiswa>, t: Throwable) {
                Toast.makeText(applicationContext,"Maaf ada kesalahan dalam server kami",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}