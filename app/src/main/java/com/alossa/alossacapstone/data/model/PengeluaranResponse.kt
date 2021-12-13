package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class PengeluaranResponse(

	@field:SerializedName("data")
	val data: List<Pengeluaran>? = null,

	@field:SerializedName("errors")
	val errors: Any? = null,

	@field:SerializedName("content")
	val content: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Pengeluaran(

	@field:SerializedName("idUser")
	val idUser: Int? = null,

	@field:SerializedName("idAlokasi")
	val idAlokasi: Int? = null,

	@field:SerializedName("danaPengeluaran")
	val danaPengeluaran: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("namaPengeluaran")
	val namaPengeluaran: String? = null
)
