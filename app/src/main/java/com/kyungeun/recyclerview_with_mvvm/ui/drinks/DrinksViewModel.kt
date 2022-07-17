package com.kyungeun.recyclerview_with_mvvm.ui.drinks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val repository: DrinkRepository
) : ViewModel() {

//    val drinkList: MutableLiveData<List<Drink>> = MutableLiveData()
//
//    init {
//        viewModelScope.launch {
//            drinkList.value = repository.getAllDrink()
//        }
//    }

    val drinkList = repository.getAllDrink()
}
