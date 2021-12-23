package com.alossa.alossacapstone.network

import com.alossa.alossacapstone.data.model.*
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
    fun addPemasukan(@Field("idUser") idUser: Int,
                     @Field("danaPemasukan") danaPemasukan: Int
    ): Call<ResponseServe>

    @FormUrlEncoded
    @POST("pemasukan/update/status")
    fun updateStatusPemasukan(
        @Field("id") id: Int,
        @Field("idUser") idUser: Int,
        @Field("status") status: Int
    ): Call<ResponseServe>

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

    @GET("wishlist/update/status/{id}/{status}")
    fun updateStatusWishlist(
        @Path("id")id:Int,
        @Path("status") status: Int
    ): Call<ResponseServe>

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

    @FormUrlEncoded
    @POST("alokasi/update/nominal")
    fun updateNominalAlokasi(@Field("id") id: Int,
                             @Field("nominal") nominal: Int,
                             @Field("namaAlokasi") namaAlokasi: String
    ) : Call<ResponseServe>

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



    //Laporan
    @GET("laporan/my/{idUser}/{bulan}/{tahun}")
    fun getLaporanBulanan(
        @Path("idUser") idUser: Int,
        @Path("bulan") bulan: Int,
        @Path("tahun") tahun: Int
    ): Call<LaporanResponse>


}