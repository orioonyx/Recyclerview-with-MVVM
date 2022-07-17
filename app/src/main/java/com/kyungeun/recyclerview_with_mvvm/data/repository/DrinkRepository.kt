package com.kyungeun.recyclerview_with_mvvm.data.repository

import androidx.lifecycle.LiveData
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkRemoteDataSource
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import com.kyungeun.recyclerview_with_mvvm.utils.performGetOperation
import retrofit2.Call
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val remoteDataSource: DrinkRemoteDataSource
) {
    suspend fun getAllDrink(): List<Drink> {
        return remoteDataSource.getAllDrink().data!!.results
    }

//    suspend fun getDrink(id: Int): Drink {
//        return remoteDataSource.getDrink(id).data!!
//    }

    suspend fun getDrink(id: Int): Drink {
        return remoteDataSource.getDrink(id).data!!
    }

}