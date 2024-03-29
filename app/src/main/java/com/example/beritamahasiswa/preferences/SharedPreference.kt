package com.example.beritamahasiswa.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREF_NAME="beritamahasiswa_pref"
    val sharedPref:SharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    fun save(KEY_NAME:String,text:String){
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME,text)
        editor.apply()
    }

    fun save(KEY_NAME: String,value:Int){
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME,value)
        editor.apply()
    }

    fun save(KEY_NAME: String,status: Boolean){
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(KEY_NAME,status)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String):String?{
        return sharedPref.getString(KEY_NAME,null)
    }

    fun getValueInt(KEY_NAME: String):Int{
        return sharedPref.getInt(KEY_NAME,0)
    }

    fun getValueBoolean(KEY_NAME: String):Boolean{
        return sharedPref.getBoolean(KEY_NAME,false)
    }

    fun clearSharedPrefrences(){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun removeSharedPreferences(){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.remove(PREF_NAME)
        editor.apply()
    }
}