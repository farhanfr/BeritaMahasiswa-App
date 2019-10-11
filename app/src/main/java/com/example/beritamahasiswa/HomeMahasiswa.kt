package com.example.beritamahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beritamahasiswa.preferences.SharedPreference
import kotlinx.android.synthetic.main.home_layout.*

class HomeMahasiswa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        val sharedPref:SharedPreference= SharedPreference(this)

        et_coba.setText(sharedPref.getValueInt("mahasiswa_id").toString())


        btn_logout.setOnClickListener {
            sharedPref.clearSharedPrefrences()
            startActivity(Intent(this,SigninMahasiswa::class.java))
            finish()
        }
    }
}