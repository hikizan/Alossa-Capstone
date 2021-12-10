package com.alossa.alossacapstone.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.model.ResponseServe

class AuthViewModel(private val alossaRepository: AlossaRepository) : ViewModel() {
    fun login(email:String, password: String): LiveData<ResponseServe> =
        alossaRepository.login(email, password)

    fun register(email: String, name: String, password: String, c_password: String): LiveData<ResponseServe> =
        alossaRepository.register(email, name, password, c_password)

    fun forgetPassword(email: String): LiveData<ResponseServe> =
        alossaRepository.forgetPassword(email)

    fun resetPassword(email: String, token: String, password: String): LiveData<ResponseServe> =
        alossaRepository.resetPassword(email, token, password)
}