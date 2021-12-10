package com.alossa.alossacapstone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.source.RemoteDataSource

class AlossaRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AlossaDataSource {

    override fun login(email: String, password: String): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.login(object : RemoteDataSource.LoadAuthCallback{
            override fun onLoadAuth(response: ResponseServe?) {
                if (response!=null){
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status
                    )
                    serverResponse.postValue(res)
                }
            }

        }, email, password)
        return serverResponse
    }

    override fun register(
        email: String,
        name: String,
        password: String,
        c_password: String
    ): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.register(object : RemoteDataSource.LoadAuthCallback{
            override fun onLoadAuth(response: ResponseServe?) {
                if (response!=null){
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status
                    )
                    serverResponse.postValue(res)
                }
            }

        }, email, name, password, c_password)
        return serverResponse
    }

    override fun forgetPassword(email: String): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.forgetPassword(object : RemoteDataSource.LoadAuthCallback{
            override fun onLoadAuth(response: ResponseServe?) {
                if (response!=null){
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status
                    )
                    serverResponse.postValue(res)
                }
            }

        }, email)
        return serverResponse
    }

    override fun resetPassword(
        email: String,
        token: String,
        password: String
    ): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.resetPassword(object : RemoteDataSource.LoadAuthCallback{
            override fun onLoadAuth(response: ResponseServe?) {
                if (response!=null){
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status
                    )
                    serverResponse.postValue(res)
                }
            }

        }, email, token, password)
        return serverResponse
    }

    companion object{
        @Volatile
        private var instance: AlossaRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AlossaRepository =
            instance ?: synchronized(this) {
                instance ?: AlossaRepository(remoteData).apply { instance = this }
            }
    }

}