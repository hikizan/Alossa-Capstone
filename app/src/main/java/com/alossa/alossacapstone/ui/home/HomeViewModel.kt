package com.alossa.alossacapstone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.model.WishList

class HomeViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {

    fun getAlokasiByIdUser(idUser: Int) : LiveData<List<Alokasi>> =
        alossaRepository.getAlokasiByIdUser(idUser)

    fun getWihsListByIdUser(idUser: Int) : LiveData<List<WishList>> =
        alossaRepository.getWishListByIdUser(idUser)

    fun addAlokasi(idUser: Int, namaAlokias: String, idPemasukan: Int, nominal: Int) =
        alossaRepository.addAlokasi(idUser, namaAlokias, idPemasukan, nominal)

    fun deleteAlokasi(idAlokasi:Int): LiveData<ResponseServe> =
        alossaRepository.deleteAlokasi(idAlokasi)

}