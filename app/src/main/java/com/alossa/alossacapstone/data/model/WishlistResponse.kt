package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class WishlistResponse(

    @field:SerializedName("data")
    val data: List<WishList>? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("content")
    val content: Any? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class WishList(

    @field:SerializedName("idUser")
    val idUser: Int? = null,

    @field:SerializedName("idAlokasi")
    val idAlokasi: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("targetDana")
    val targetDana: Int = 0,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("durasi")
    val durasi: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null
)
