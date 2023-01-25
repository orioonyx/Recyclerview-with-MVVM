package com.kyungeun.recyclerview_with_mvvm

import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink

object ValidationUtil {

    fun validateDrink(drink : Drink) : Boolean {
        if (drink.id != null && drink.name.isNotEmpty() && drink.alcoholic.isNotEmpty() && drink.category.isNotEmpty() && drink.image.isNotEmpty() && drink.info.isNotEmpty()) {
            return true
        }
        return false
    }
}