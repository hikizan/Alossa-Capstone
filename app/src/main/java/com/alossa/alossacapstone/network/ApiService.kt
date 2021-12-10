package com.alossa.alossacapstone.network

import com.alossa.alossacapstone.data.model.PemasukanResponse
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
                 @Field("c_password") c_password:String,
                 @Field("nohp") nohp:String) : Call<ResponseServe>

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
    @GET("pemasukan/my/{idUser}")
    fun getPengeluaranById(
        @Path("idUser") idUser: Int
    )

    @FormUrlEncoded
    @POST("pemasukan/tambah")
    fun addPengeluaran(

    )

    //Alokasi
    @GET("alokasi/my/{idUser}")
    fun getAlokasid(
        @Path("idUser") idUser: Int
    )

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