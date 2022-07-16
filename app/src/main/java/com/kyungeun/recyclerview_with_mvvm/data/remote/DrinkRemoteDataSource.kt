package com.kyungeun.recyclerview_with_mvvm.data.remote

import javax.inject.Inject

class DrinkRemoteDataSource @Inject constructor(
    private val drinkService: DrinkService
): BaseDataSource() {

    suspend fun getAllDrink() = getResult { drinkService.getAllDrinks() }

    suspend fun getDrink(id: Int) = getResult { drinkService.getDrink(id) }
}