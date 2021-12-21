package com.alossa.alossacapstone.ui.home

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.Pemasukan
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.model.WishList

class HomeViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {
    private val pemasukanId = MutableLiveData<Int>()

    fun setPemasukanId(mPemasukanId: Int) {
        this.pemasukanId.setValue(mPemasukanId)
    }

    fun getPemasukanId(): Int? {
        return pemasukanId.getValue()
    }

    fun getAlokasiByIdUser(idUser: Int) : LiveData<List<Alokasi>> =
        alossaRepository.getAlokasiByIdUser(idUser)

    fun getWihsListByIdUser(idUser: Int) : LiveData<List<WishList>> =
        alossaRepository.getWishListByIdUser(idUser)

    /*
    fun addAlokasi(idUser: Int, namaAlokias: String, idPemasukan: Int, nominal: Int) =
        alossaRepository.addAlokasi(idUser, namaAlokias, idPemasukan, nominal)
     */
    fun addAlokasi(idUser: Int, namaAlokias: String, idPemasukan: Int, nominal: Int) =
        alossaRepository.addAlokasi(idUser, namaAlokias, idPemasukan, nominal)

    fun deleteAlokasi(idAlokasi:Int): LiveData<ResponseServe> =
        alossaRepository.deleteAlokasi(idAlokasi)

    fun addPemasukan(idUser: Int, danaPemasukan: Int) =
        alossaRepository.addPemasukan(idUser, danaPemasukan)

    fun getPemasukanByIdUser(id: Int): LiveData<List<Pemasukan>> =
        alossaRepository.getPemasukanById(id)

    fun updateStatusPemasukan(id:Int, idUser:Int, status: Int): LiveData<ResponseServe> =
        alossaRepository.updateStatusPemasukan(id, idUser, status)
}