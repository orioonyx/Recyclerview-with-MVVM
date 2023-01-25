package com.kyungeun.recyclerview_with_mvvm.data.entities

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("idDrink") val id: Int?,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strAlcoholic") val alcoholic: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strDrinkThumb") val image: String,
    @SerializedName("strInstructions") val info: String,
    @SerializedName("dateModified") val dateModified: String?
)
