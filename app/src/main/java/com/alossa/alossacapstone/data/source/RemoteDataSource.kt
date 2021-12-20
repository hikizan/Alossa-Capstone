package com.alossa.alossacapstone.data.source

import android.util.Log
import com.alossa.alossacapstone.data.model.*
import com.alossa.alossacapstone.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun login(callback: LoadAuthCallback, email: String, password: String) {
        ApiConfig.getApiService().login(email, password)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAuth(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun register(
        callback: LoadAuthCallback,
        email: String,
        name: String,
        password: String,
        c_password: String
    ) {
        ApiConfig.getApiService().register(email, name, password, c_password)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAuth(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun forgetPassword(callback: LoadAuthCallback, email: String) {
        ApiConfig.getApiService().lupaPassowrd(email)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAuth(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun resetPassword(callback: LoadAuthCallback, email: String, token: String, password: String) {
        ApiConfig.getApiService().resetPassword(email, token, password)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAuth(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun getPemasukanById(callback: LoadPemasukanCallback, id: Int) {
        ApiConfig.getApiService().getPemasukanById(id)
            .enqueue(object : Callback<PemasukanResponse> {
                override fun onResponse(
                    call: Call<PemasukanResponse>,
                    response: Response<PemasukanResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadPemasukan(response.body()?.data)
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<PemasukanResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun addPemasukan(callback: LoadAddPemasukanCallback, idUser: Int, danaPemasukan: Int){
        ApiConfig.getApiService().addPemasukan(idUser, danaPemasukan)
            .enqueue(object : Callback<ResponseServe>{
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadAddPemasukan(response.body())
                        Log.d("success", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }


    fun getAlokasiByIdUser(callback: LoadAlokasiCallback, idUser: Int) {
        ApiConfig.getApiService().getAlokasByIdUser(idUser)
            .enqueue(object : Callback<AlokasiResponse> {
                override fun onResponse(
                    call: Call<AlokasiResponse>,
                    response: Response<AlokasiResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAlokasi(response.body()?.data)
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<AlokasiResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun getWishListByIdUser(callback: LoadWishListCallback, idUser: Int) {
        ApiConfig.getApiService().getWishlistById(idUser)
            .enqueue(object : Callback<WishlistResponse> {
                override fun onResponse(
                    call: Call<WishlistResponse>,
                    response: Response<WishlistResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadWishList(response.body()?.data)
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<WishlistResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }
            })
    }

    fun addWishList(
        callback: LoadAddWishList,
        idUser: Int,
        namaBarang: String,
        idAlokasi: Int,
        targetDana: Int,
        durasi: Int,
        status: Int
    ) {

        ApiConfig.getApiService().addWishlist(idUser, namaBarang, idAlokasi, targetDana, durasi, status)
            .enqueue(object :Callback<ResponseServe>{
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onAddWishList(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }


    fun getPengeluaranByIdUser(callback: LoadPengeluaranCallback, idUser: Int) {
        ApiConfig.getApiService().getPengeluaranByIdUser(idUser)
            .enqueue(object : Callback<PengeluaranResponse> {
                override fun onResponse(
                    call: Call<PengeluaranResponse>,
                    response: Response<PengeluaranResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadPengeluaran(response.body()?.data)
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<PengeluaranResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }
            })
    }

    fun addAlokasi(
        callback: LoadAddAlokasi,
        idUser: Int,
        namaAlokias: String,
        idPemasukan: Int,
        nominal: Int
    ) {
        ApiConfig.getApiService().addAlokasi(idUser, namaAlokias, idPemasukan, nominal)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful) {
                        callback.onLoadAlokasi(response.body())
                        Log.d("succes", response.code().toString())
                    } else {
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun deleteAlokasi(callback: LoadAddAlokasi, idAlokasi: Int) {
        ApiConfig.getApiService().deleteAlokasi(idAlokasi)
            .enqueue(object : Callback<ResponseServe> {
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    callback.onLoadAlokasi(response.body())
                    Log.d("succes", response.code().toString())
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }


    fun addPengeluaran(callback: LoadAddPengeluaranCallback, idUser: Int, idAlokasi: Int, danaPengeluaran: Int, namaPengeluaran: String){
        ApiConfig.getApiService().addPengeluaran(idUser, idAlokasi, danaPengeluaran, namaPengeluaran)
            .enqueue(object : Callback<ResponseServe>{
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadAddPengeluaran(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })

    }

    fun updateNominalAlokasi(callback: LoadUpdateNominalAlokasiCallback, id: Int, nominal: Int, namaAlokasi: String){
        ApiConfig.getApiService().updateNominalAlokasi(id, nominal, namaAlokasi)
            .enqueue(object : Callback<ResponseServe>{
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadUpdateNominalAlokasi(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun getLaporanBulanan(callback: LoadLaporanBulanan, idUser: Int, bulan: Int, tahun:Int){
        ApiConfig.getApiService().getLaporanBulanan(idUser, bulan, tahun)
            .enqueue(object : Callback<LaporanResponse>{
                override fun onResponse(
                    call: Call<LaporanResponse>,
                    response: Response<LaporanResponse>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadLaporanBulanan(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<LaporanResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun updateWihslistStatus(callback: LoadUpdateSatatusWishList, id:Int, status: Int){
        ApiConfig.getApiService().updateStatusWishlist(id, status)
            .enqueue(object : Callback<ResponseServe>{
                override fun onResponse(
                    call: Call<ResponseServe>,
                    response: Response<ResponseServe>
                ) {
                    if (response.isSuccessful){
                        callback.onUpdateStatusishList(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseServe>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    interface LoadAuthCallback{
        fun onLoadAuth(response: ResponseServe?)
    }

    interface LoadPemasukanCallback {
        fun onLoadPemasukan(response: List<Pemasukan>?)
    }

    interface LoadAddPemasukanCallback {
        fun onLoadAddPemasukan(response: ResponseServe?)
    }

    interface LoadAlokasiCallback {
        fun onLoadAlokasi(response: List<Alokasi>?)
    }

    interface LoadWishListCallback {
        fun onLoadWishList(response: List<WishList>?)
    }

    interface LoadAddWishList {
        fun onAddWishList(response: ResponseServe?)
    }

    interface LoadUpdateSatatusWishList{
        fun onUpdateStatusishList(response: ResponseServe?)
    }

    interface LoadAddAlokasi {
        fun onLoadAlokasi(response: ResponseServe?)
    }

    interface LoadPengeluaranCallback {
        fun onLoadPengeluaran(response: List<Pengeluaran>?)
    }

    interface LoadAddPengeluaranCallback{
        fun onLoadAddPengeluaran(response: ResponseServe?)
    }

    interface LoadUpdateNominalAlokasiCallback{
        fun onLoadUpdateNominalAlokasi(response: ResponseServe?)
    }

    interface LoadLaporanBulanan{
        fun onLoadLaporanBulanan(response: LaporanResponse?)
    }


    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}