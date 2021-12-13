package com.alossa.alossacapstone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.data.model.Pemasukan
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.source.RemoteDataSource

class AlossaRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AlossaDataSource {

    override fun login(email: String, password: String): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.login(object : RemoteDataSource.LoadAuthCallback {
            override fun onLoadAuth(response: ResponseServe?) {
                if (response != null) {
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status,
                        id = response.id,
                        email = response.email
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
        remoteDataSource.register(object : RemoteDataSource.LoadAuthCallback {
            override fun onLoadAuth(response: ResponseServe?) {
                if (response != null) {
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
        remoteDataSource.forgetPassword(object : RemoteDataSource.LoadAuthCallback {
            override fun onLoadAuth(response: ResponseServe?) {
                if (response != null) {
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
        remoteDataSource.resetPassword(object : RemoteDataSource.LoadAuthCallback {
            override fun onLoadAuth(response: ResponseServe?) {
                if (response != null) {
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

    override fun getPemasukanById(id: Int): LiveData<List<Pemasukan>> {
        val pemasukanResult = MutableLiveData<List<Pemasukan>>()
        remoteDataSource.getPemasukanById(object : RemoteDataSource.LoadPemasukanCallback {
            override fun onLoadPemasukan(response: List<Pemasukan>?) {
                val pemasukanList = ArrayList<Pemasukan>()
                if (response != null) {
                    for (pemasukanResponse in response) {
                        val pemasukan = Pemasukan(
                            danaPemasukan = pemasukanResponse.danaPemasukan,
                            createdAt = pemasukanResponse.createdAt
                        )
                        pemasukanList.add(pemasukan)
                    }
                    pemasukanResult.postValue(pemasukanList)
                }
            }

        }, id)
        return pemasukanResult
    }

    override fun getAlokasiByIdUser(idUser: Int): LiveData<List<Alokasi>> {
        val alokasiResult = MutableLiveData<List<Alokasi>>()
        remoteDataSource.getAlokasiByIdUser(object : RemoteDataSource.LoadAlokasiCallback {
            override fun onLoadAlokasi(response: List<Alokasi>?) {
                val alokasiList = ArrayList<Alokasi>()
                if (response != null) {
                    for (alokasiResponse in response) {
                        val alokasi = Alokasi(
                            nominal = alokasiResponse.nominal,
                            createdAt = alokasiResponse.createdAt,
                            namaAlokasi = alokasiResponse.namaAlokasi
                        )
                        alokasiList.add(alokasi)
                    }
                    alokasiResult.postValue(alokasiList)
                }
            }

        }, idUser)
        return alokasiResult
    }

    companion object {
        @Volatile
        private var instance: AlossaRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AlossaRepository =
            instance ?: synchronized(this) {
                instance ?: AlossaRepository(remoteData).apply { instance = this }
            }
    }

}