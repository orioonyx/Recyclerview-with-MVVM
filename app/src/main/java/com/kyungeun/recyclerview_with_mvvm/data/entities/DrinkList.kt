package com.kyungeun.recyclerview_with_mvvm.data.entities

import com.google.gson.annotations.SerializedName

data class DrinkList (
    @SerializedName("drinks") val  results: List<Drink>
)