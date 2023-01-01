package com.kyungeun.recyclerview_with_mvvm.ui.drinkdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kyungeun.recyclerview_with_mvvm.data.entities.DrinkList
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val repository: DrinkRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _drink = _id.switchMap { id ->
        repository.getDrink(id)
    }

    val drink: LiveData<Resource<DrinkList>> = _drink

    fun start(id: Int) {
        _id.value = id
    }
}
