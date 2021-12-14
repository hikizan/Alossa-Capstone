package com.alossa.alossacapstone.ui.expenditure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.Pemasukan
import com.alossa.alossacapstone.data.model.Pengeluaran
import com.alossa.alossacapstone.data.model.ResponseServe

class ExpenditureViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {

    //fun getExpendetureById(id: Int) : LiveData<List<Pemasukan>> = alossaRepository.getPemasukanById(id)

    //fun getAlokasiById(id: Int) : LiveData<List<Alokasi>> = alossaRepository.getAlokasiByIdUser(id)

    fun getPengeluaranByIdUser(idUser: Int): LiveData<List<Pengeluaran>> =
        alossaRepository.getPengeluaranByIdUser(idUser)

    fun addPengeluaran(idUser: Int, idAlokasi: Int, danaPengeluaran: Int, namaPengeluaran: String): LiveData<ResponseServe> =
        alossaRepository.addPengeluaran(idUser, idAlokasi, danaPengeluaran, namaPengeluaran)
}