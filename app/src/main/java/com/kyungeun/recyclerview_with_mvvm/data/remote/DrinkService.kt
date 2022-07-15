package com.kyungeun.recyclerview_with_mvvm.data.remote

import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.entities.DrinkList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DrinkService {

    @GET("json/v1/1/search.php?s=margarita")
    suspend fun getAllDrinks() : Response<DrinkList>

    @GET("json/v1/1/lookup.php/{id}")
    suspend fun getDrink(@Path("id") id: Int): Response<Drink>
}