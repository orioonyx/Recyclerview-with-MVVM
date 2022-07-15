package com.kyungeun.recyclerview_with_mvvm.data.repository

import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkRemoteDataSource
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val remoteDataSource: DrinkRemoteDataSource
) {
    suspend fun getDrink(): List<Drink> {
        return remoteDataSource.getDrink().data!!.results
    }
}