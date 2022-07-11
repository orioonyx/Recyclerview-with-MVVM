package com.kyungeun.recyclerview_with_mvvm.ui.characterdetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kyungeun.recyclerview_with_mvvm.data.entities.Character
import com.kyungeun.recyclerview_with_mvvm.data.repository.CharacterRepository
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<Character>> = _character


    fun start(id: Int) {
        _id.value = id
    }

}
