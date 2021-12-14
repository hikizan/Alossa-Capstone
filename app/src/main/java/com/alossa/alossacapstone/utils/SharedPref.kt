package com.alossa.alossacapstone.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(mContext : Context)  {
    val login = "session_status"
    val id = "id"
    private val mypref = "MAIN_PRF"
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

    fun getStatusLogin() : Boolean{
        return sp.getBoolean(login, false)
    }

    fun logout(){
        sp.edit().putBoolean(login, false).apply()
        sp.edit().putInt(id,0).apply()
    }
}