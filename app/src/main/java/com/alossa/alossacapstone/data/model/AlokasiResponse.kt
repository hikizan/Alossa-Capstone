package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class AlokasiResponse(

    @field:SerializedName("data")
    val data: List<Alokasi>? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("content")
    val content: Any? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Alokasi(

    @field:SerializedName("idUser")
    val idUser: Int? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("namaAlokasi")
    val namaAlokasi: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("idPemasukan")
    val idPemasukan: Int? = null
)
