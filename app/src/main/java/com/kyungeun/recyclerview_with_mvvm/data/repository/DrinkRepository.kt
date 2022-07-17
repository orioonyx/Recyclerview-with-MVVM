package com.kyungeun.recyclerview_with_mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.entities.DrinkList
import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkRemoteDataSource
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val remoteDataSource: DrinkRemoteDataSource
) {
//    suspend fun getAllDrink(): List<Drink> {
//        return remoteDataSource.getAllDrink().data!!.results
//    }

    fun getAllDrink(): LiveData<Resource<DrinkList>> = liveData(Dispatchers.IO) {
        val data = MutableLiveData<Resource<DrinkList>>()
        data.postValue(remoteDataSource.getAllDrink())
        emitSource(data)
    }

    fun getDrink(id: Int): LiveData<Resource<DrinkList>> = liveData(Dispatchers.IO) {
        val data = MutableLiveData<Resource<DrinkList>>()
        data.postValue(remoteDataSource.getDrink(id))
        emitSource(data)
    }
}