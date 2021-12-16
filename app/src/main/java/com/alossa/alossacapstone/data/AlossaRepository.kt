package com.alossa.alossacapstone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alossa.alossacapstone.data.model.*
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
                            id = alokasiResponse.id,
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

    override fun addAlokasi(
        idUser: Int,
        namaAlokias: String,
        idPemasukan: Int,
        nominal: Int
    ): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.addAlokasi(object : RemoteDataSource.LoadAddAlokasi {
            override fun onLoadAlokasi(response: ResponseServe?) {
                if (response != null) {
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status,
                    )
                    serverResponse.postValue(res)
                }
            }

        }, idUser, namaAlokias, idPemasukan, nominal)
        return serverResponse
    }

    override fun deleteAlokasi(idAlokasi: Int): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.deleteAlokasi(object : RemoteDataSource.LoadAddAlokasi {
            override fun onLoadAlokasi(response: ResponseServe?) {
                if (response != null) {
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status,
                    )
                    serverResponse.postValue(res)
                }
            }

        }, idAlokasi)
        return serverResponse
    }

    override fun getWishListByIdUser(idUser: Int): LiveData<List<WishList>> {
        val wishListResult = MutableLiveData<List<WishList>>()
        remoteDataSource.getWishListByIdUser(object : RemoteDataSource.LoadWishListCallback {
            override fun onLoadWishList(response: List<WishList>?) {
                val wishListList = ArrayList<WishList>()
                if (response != null) {
                    for (wishlistResponse in response) {
                        val wishList = WishList(
                            targetDana = wishlistResponse.targetDana,
                            createdAt = wishlistResponse.createdAt,
                            id = wishlistResponse.id,
                            status = wishlistResponse.status,
                            durasi =  wishlistResponse.durasi,
                            namaBarang = wishlistResponse.namaBarang
                        )
                        wishListList.add(wishList)
                    }
                    wishListResult.postValue(wishListList)
                      }
            }

        }, idUser)
       return wishListResult
    }

    override fun addWishList(
        idUser: Int,
        namaBarang: String,
        idAlokasi: Int,
        targetDana: Int,
        durasi: Int,
        status: Int
    ): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.addWishList(object : RemoteDataSource.LoadAddWishList {
            override fun onAddWishList(response: ResponseServe?) {
                if (response != null) {
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status,
                    )
                    serverResponse.postValue(res)
                }
            }
        }, idUser, namaBarang, idAlokasi, targetDana, durasi, status)
        return serverResponse
    }

    override fun getPengeluaranByIdUser(idUser: Int): LiveData<List<Pengeluaran>> {
        val pengeluaranResult = MutableLiveData<List<Pengeluaran>>()
        remoteDataSource.getPengeluaranByIdUser(object : RemoteDataSource.LoadPengeluaranCallback {
            override fun onLoadPengeluaran(response: List<Pengeluaran>?) {
                val pengeluaranList = ArrayList<Pengeluaran>()
                if (response != null){
                    for (pengeluaranResponse in response){
                        val pengeluaran = Pengeluaran(
                            namaPengeluaran = pengeluaranResponse.namaPengeluaran,
                            danaPengeluaran = pengeluaranResponse.danaPengeluaran,
                            idAlokasi = pengeluaranResponse.idAlokasi,
                            createdAt = pengeluaranResponse.createdAt,
                            namaAlokasi = pengeluaranResponse.namaAlokasi
                        )
                        pengeluaranList.add(pengeluaran)
                    }
                    pengeluaranResult.postValue(pengeluaranList)
                }
            }

        }, idUser)
          return pengeluaranResult
    }
      

    override fun addPengeluaran(
        idUser: Int,
        idAlokasi: Int,
        danaPengeluaran: Int,
        namaPengeluaran: String
    ): LiveData<ResponseServe> {
        val serverResponse = MutableLiveData<ResponseServe>()
        remoteDataSource.addPengeluaran(object : RemoteDataSource.LoadAddPengeluaranCallback {
            override fun onLoadAddPengeluaran(response: ResponseServe?) {
                if (response != null){
                    val res = ResponseServe(
                        msg = response.msg,
                        status = response.status
                    )
                    serverResponse.postValue(res)
                }
            }

        }, idUser, idAlokasi, danaPengeluaran, namaPengeluaran)

        return serverResponse
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