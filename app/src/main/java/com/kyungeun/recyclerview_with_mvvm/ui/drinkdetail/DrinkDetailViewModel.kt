package com.kyungeun.recyclerview_with_mvvm.ui.drinkdetail


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val repository: DrinkRepository
) : ViewModel() {


}
