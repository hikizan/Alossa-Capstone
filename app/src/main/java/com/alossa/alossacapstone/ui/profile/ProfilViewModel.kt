package com.alossa.alossacapstone.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.model.WishList

class ProfilViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {
    fun getWishlistById(idUser: Int): LiveData<List<WishList>> =
        alossaRepository.getWishListByIdUser(idUser)

    fun addWishList(idUser: Int,namaBarang: String,idAlokasi: Int,  targetDana: Int, durasi:Int, status: Int): LiveData<ResponseServe> =
        alossaRepository.addWishList(idUser, namaBarang, idAlokasi, targetDana, durasi, status)
}