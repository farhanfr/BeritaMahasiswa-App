package com.example.beritamahasiswa.retrofit.response

import com.example.beritamahasiswa.retrofit.model.MahasiswaModel

data class ResponseSignupMahasiswa(val status:Int, val data:List<MahasiswaModel>, val msg:String)