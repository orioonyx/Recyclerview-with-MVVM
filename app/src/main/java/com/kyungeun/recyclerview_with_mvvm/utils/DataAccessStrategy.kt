package com.kyungeun.recyclerview_with_mvvm.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val response = networkCall.invoke()
        val data = MutableLiveData<Resource<T>>()
        data.postValue(response)

        if (response.status == Resource.Status.SUCCESS) {
            emitSource(data)
        } else if (response.status == Resource.Status.ERROR) {
            emit(Resource.error(response.message!!))
            emitSource(data)
        }
    }