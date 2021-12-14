package com.alossa.alossacapstone.data

import androidx.lifecycle.LiveData
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.Pemasukan
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.model.WishList

interface AlossaDataSource {

    //Auth
    fun login(email: String, password: String): LiveData<ResponseServe>

    fun register(email: String, name: String, password: String, c_password: String): LiveData<ResponseServe>

    fun forgetPassword(email: String): LiveData<ResponseServe>

    fun resetPassword(email: String, token:String, password: String): LiveData<ResponseServe>

    //Pemasukan
    fun getPemasukanById(id: Int): LiveData<List<Pemasukan>>

    //Alokasi
    fun getAlokasiByIdUser(id: Int): LiveData<List<Alokasi>>

    fun addAlokasi(idUser: Int, namaAlokias: String, idPemasukan: Int, nominal: Int): LiveData<ResponseServe>

    //WishList
    fun getWishListByIdUser(id: Int): LiveData<List<WishList>>

}