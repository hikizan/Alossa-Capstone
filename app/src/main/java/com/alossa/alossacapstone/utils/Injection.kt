package com.alossa.alossacapstone.utils

import com.alossa.alossacapstone.data.AlossaRepository
import com.alossa.alossacapstone.data.source.RemoteDataSource

object Injection {
    fun provideRepository(): AlossaRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return AlossaRepository.getInstance(remoteDataSource)
    }
}