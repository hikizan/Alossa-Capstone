package com.alossa.alossacapstone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.Alokasi

class HomeViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {

    fun getAlokasiByIdUser(idUser: Int) : LiveData<List<Alokasi>> =
        alossaRepository.getAlokasiByIdUser(idUser)

}