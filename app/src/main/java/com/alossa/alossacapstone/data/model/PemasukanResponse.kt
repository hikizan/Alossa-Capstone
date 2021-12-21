package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class PemasukanResponse(

    @field:SerializedName("data")
    val data: List<Pemasukan>? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("content")
    val content: Any? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Pemasukan(

    @field:SerializedName("idUser")
    val idUser: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: Any? = null,

    @field:SerializedName("danaPemasukan")
    val danaPemasukan: Int = 0,

    @field:SerializedName("created_at")
    val createdAt: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("status")
    val status: Int = 0
)
