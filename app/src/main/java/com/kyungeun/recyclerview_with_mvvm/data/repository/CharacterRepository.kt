package com.kyungeun.recyclerview_with_mvvm.data.repository

import com.kyungeun.recyclerview_with_mvvm.data.local.CharacterDao
import com.kyungeun.recyclerview_with_mvvm.data.remote.CharacterRemoteDataSource
import com.kyungeun.recyclerview_with_mvvm.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}