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
    val status: String? = null
)