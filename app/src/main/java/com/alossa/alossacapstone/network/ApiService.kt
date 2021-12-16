package com.alossa.alossacapstone.network

import com.alossa.alossacapstone.data.model.AlokasiResponse
import com.alossa.alossacapstone.data.model.PemasukanResponse
import com.alossa.alossacapstone.data.model.PengeluaranResponse
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.data.model.WishlistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiService {
    //Auth
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email :String,
              @Field("password") password :String) : Call<ResponseServe>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("email") email :String,
                 @Field("name") name :String,
                 @Field("password") password: String,
                 @Field("c_password") c_password:String
    ) : Call<ResponseServe>

    @FormUrlEncoded
    @POST("password/create")
    fun lupaPassowrd(@Field("email")email :String) : Call<ResponseServe>

    @FormUrlEncoded
    @POST("password/reset")
    fun resetPassword(@Field("email") email: String,
                      @Field("token") token:String,
                      @Field("password")password: String) : Call<ResponseServe>

    //Pemasukan
    @GET("pemasukan/all/{idUser}")
    fun getPemasukanById(
        @Path("idUser") idUser: Int
    ) : Call<PemasukanResponse>

    @FormUrlEncoded
    @POST("pemasukan/tambah")
    fun addExpenditure(

    ): Call<PemasukanResponse>

    //Wishlist
    @GET("wishlist/my/{idUser}")
    fun getWishlistById(
        @Path("idUser") idUser: Int
    ): Call<WishlistResponse>

    @FormUrlEncoded
    @POST("wishlist/tambah")
    fun addWishlist(
        @Field("idUser") idUser: Int,
        @Field("namaBarang") namaBarang: String,
        @Field("idAlokasi") idAlokasi: Int,
        @Field("targetDana") targetDana: Int,
        @Field("durasi") durasi: Int,
        @Field("status") status: Int
    ): Call<ResponseServe>

    //Pengeluaran
    @GET("pengeluaran/my/{idUser}")
    fun getPengeluaranByIdUser(
        @Path("idUser") idUser: Int
    ): Call<PengeluaranResponse>

    @FormUrlEncoded
    @POST("pengeluaran/tambah")
    fun addPengeluaran(

    )

    //Alokasi
    @GET("alokasi/my/{idUser}")
    fun getAlokasByIdUser(
        @Path("idUser") idUser: Int
    ): Call<AlokasiResponse>

    @FormUrlEncoded
    @POST("alokasi/tambah")
    fun addAlokasi(
        @Field("idUser") idUser: Int,
        @Field("namaAlokasi") namaAlokias: String,
        @Field("idPemasukan") idPemasukan: Int,
        @Field("nominal") nominal: Int
    ): Call<ResponseServe>

    @GET("alokasi/hapus/{idAlokasi}")
    fun deleteAlokasi(
        @Path("idAlokasi") idAlokasi: Int
    ): Call<ResponseServe>

    @FormUrlEncoded
    @POST()
    fun updateAlokasi(

    )



}