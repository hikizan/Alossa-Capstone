package com.alossa.alossacapstone.network

import com.alossa.alossacapstone.data.model.AlokasiResponse
import com.alossa.alossacapstone.data.model.PemasukanResponse
import com.alossa.alossacapstone.data.model.PengeluaranResponse
import com.alossa.alossacapstone.data.model.ResponseServe
import retrofit2.Call
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
    @GET("pemasukan/my/{idUser}")
    fun getWishlistById(
        @Path("idUser") idUser: Int
    )

    @FormUrlEncoded
    @POST("pemasukan/tambah")
    fun addWishlist(

    )

    //Pengeluaran
    @GET("pengeluaran/my/{idUser}")
    fun getPengeluaranByIdUser(
        @Path("idUser") idUser: Int
    ): Call<PengeluaranResponse>

    @FormUrlEncoded
    @POST("pengeluaran/tambah")
    fun addPengeluaran(@Field("idUser") idUser: Int,
                       @Field("idAlokasi") idAlokasi: Int,
                       @Field("danaPengeluaran") danaPengeluaran: Int,
                       @Field("namaPengeluaran") namaPengeluaran: String
    ) : Call<ResponseServe>

    //Alokasi

    @GET("alokasi/my/{idUser}")
    fun getAlokasByIdUser(
        @Path("idUser") idUser: Int
    ): Call<AlokasiResponse>

    @FormUrlEncoded
    @POST("alokasi/tambah")
    fun addAlokasi(

    )

    @GET()
    fun deleteAlokasi(

    )

    @FormUrlEncoded
    @POST()
    fun updateAlokasi(

    )



}