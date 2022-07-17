package com.kyungeun.recyclerview_with_mvvm.ui.drinks

import androidx.lifecycle.ViewModel
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val repository: DrinkRepository
) : ViewModel() {

    val drinkList = repository.getAllDrink()
}
