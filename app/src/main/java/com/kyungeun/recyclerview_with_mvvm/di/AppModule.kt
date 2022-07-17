package com.kyungeun.recyclerview_with_mvvm.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkRemoteDataSource
import com.kyungeun.recyclerview_with_mvvm.data.remote.DrinkService
import com.kyungeun.recyclerview_with_mvvm.data.remote.NullOnEmptyConverterFactory
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //https://www.thecocktaildb.com/api.php use free api
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/")
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): DrinkService = retrofit.create(DrinkService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: DrinkService) = DrinkRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: DrinkRemoteDataSource
    ) =
        DrinkRepository(remoteDataSource)
}