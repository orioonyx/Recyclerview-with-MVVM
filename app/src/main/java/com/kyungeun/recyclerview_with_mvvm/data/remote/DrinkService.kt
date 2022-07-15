package com.kyungeun.recyclerview_with_mvvm.data.remote

import com.kyungeun.recyclerview_with_mvvm.data.entities.DrinkList
import retrofit2.Response
import retrofit2.http.GET

interface DrinkService {

    @GET("json/v1/1/search.php?s=margarita")
    suspend fun getAllDrinks() : Response<DrinkList>

}