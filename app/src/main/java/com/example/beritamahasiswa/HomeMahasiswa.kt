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

        et_mahasiswaName.setText(sharedPref.getValueString("mahasiswa_name"))
        et_mahasiswaNim.setText(sharedPref.getValueString("mahasiswa_nim"))

        cv_news.setOnClickListener {
            startActivity(Intent(this,NewsMahasiswa::class.java))
        }
    }
}