package com.alossa.alossacapstone.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(mContext : Context)  {
    private val sp: SharedPreferences

    init {
        sp = mContext.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status : Boolean){
        sp.edit().putBoolean(login, status).apply()
    }

    fun setId(idUser:Int ? = 0){
        sp.edit().putInt(id,idUser!!).apply()
    }

    fun getId():Int{
        return sp.getInt(id,0)
    }

    fun setEmail(emailUser: String ? = null){
        sp.edit().putString(email, emailUser!!).apply()
    }

    fun getEmail(): String{
        return sp.getString(email, "").toString()
    }

    fun setName(nameUser: String ? = null){
        sp.edit().putString(name, nameUser!!).apply()
    }

    fun getName(): String{
        return sp.getString(name, "").toString()
    }

    fun getStatusLogin() : Boolean{
        return sp.getBoolean(login, false)
    }

    fun logout(){
        sp.edit().putBoolean(login, false).apply()
        sp.edit().putInt(id,0).apply()
        sp.edit().putString(email, "").apply()
        sp.edit().putString(name, "").apply()
    }

    companion object{
        val login = "session_status"
        val id = "id"
        val email = "email"
        val name = "name"
        private val mypref = "MAIN_PRF"
    }
}