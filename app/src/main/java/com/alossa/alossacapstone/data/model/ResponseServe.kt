package com.alossa.alossacapstone.data.model

import com.google.gson.annotations.SerializedName

data class ResponseServe(

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("errors")
    val errors: Any? = null,

    @field:SerializedName("content")
    val content: Any? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("access_token")
    val accessToken: String? = null,

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nohp")
    val nohp: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("token_type")
    val tokenType: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

)