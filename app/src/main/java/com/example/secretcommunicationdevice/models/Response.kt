package com.example.secretcommunicationdevice.models

import com.google.gson.annotations.SerializedName

data class ResponseData<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: T
)

//data class ResponseData(
//    @SerializedName("success") val success: Boolean,
//    @SerializedName("data") val data: String
//)

data class Response(
    @SerializedName("success") val success: Boolean
)