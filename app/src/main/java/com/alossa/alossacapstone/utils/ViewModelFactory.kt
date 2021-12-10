package com.alossa.alossacapstone.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.ui.auth.AuthViewModel

class ViewModelFactory private constructor(private val mAlossaRepository: AlossaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(mAlossaRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }
}