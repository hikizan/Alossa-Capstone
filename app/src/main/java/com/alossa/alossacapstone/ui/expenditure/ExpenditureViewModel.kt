package com.alossa.alossacapstone.ui.expenditure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.Pemasukan

class ExpenditureViewModel (private val alossaRepository: AlossaRepository) : ViewModel() {
    fun getExpendetureById(id: Int) : LiveData<List<Pemasukan>> =
        alossaRepository.getPemasukanById(id)

    fun getAlokasiById(id: Int) : LiveData<List<Alokasi>> =
        alossaRepository.getAlokasiByIdUser(id)
}