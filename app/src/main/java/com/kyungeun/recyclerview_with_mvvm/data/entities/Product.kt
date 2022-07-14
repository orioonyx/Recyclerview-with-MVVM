package com.kyungeun.recyclerview_with_mvvm.data.entities

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("id") val  id: Int,
    @SerializedName("title") val  title: String,
    @SerializedName("category") val  category: String,
    @SerializedName("description") val  description: String,
    @SerializedName("image") val  image: String
)