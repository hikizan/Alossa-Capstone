package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class LaporanResponse(

    @field:SerializedName("sisa")
    val sisa: Int? = null,

    @field:SerializedName("pemasukan")
    val pemasukan: Int? = null,

    @field:SerializedName("alokasi")
    val alokasi: List<Alokasi> = arrayListOf(),

    @field:SerializedName("pengeluaran")
    val pengeluaran: Int? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("content")
    val content: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("msg")
    val msg: String? = null,

)
