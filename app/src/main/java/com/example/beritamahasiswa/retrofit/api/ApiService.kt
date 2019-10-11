package com.example.beritamahasiswa.retrofit.api

import com.example.beritamahasiswa.retrofit.response.ResponseSigninMahasiswa
import com.example.beritamahasiswa.retrofit.response.ResponseSignupMahasiswa
import retrofit2.Call;
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/addmahasiswa")
    fun add_mahasiswa(
        @Field("mahasiswa_name") mahasiswa_name:String,
        @Field("mahasiswa_nim") mahasiswa_nim:String,
        @Field("mahasiswa_class") mahasiswa_class:String,
        @Field("mahasiswa_born") mahasiswa_born:String,
        @Field("mahasiswa_password") mahasiswa_password:String
    ):Call<ResponseSignupMahasiswa>

    @FormUrlEncoded
    @POST("/loginmahasiswa")
    fun login_mahasiswa(
        @Field("mahasiswa_nim") mahasiswa_nim: String,
        @Field("mahasiswa_password") mahasiswa_password: String
    ):Call<ResponseSigninMahasiswa>

}