package com.kyungeun.recyclerview_with_mvvm.ui.drinkdetail


import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.data.entities.DrinkList
import com.kyungeun.recyclerview_with_mvvm.data.repository.DrinkRepository
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
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
