package com.kyungeun.recyclerview_with_mvvm.data.repository

import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkRemoteDataSource
import com.kyungeun.recyclerview_with_mvvm.utils.performGetOperation
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val remoteDataSource: DrinkRemoteDataSource
) {
    fun getAllDrink() = performGetOperation(
        networkCall = { remoteDataSource.getAllDrink() }
    )
    fun getDrink(id: Int) = performGetOperation(
        networkCall = { remoteDataSource.getDrink(id) }
    )
}