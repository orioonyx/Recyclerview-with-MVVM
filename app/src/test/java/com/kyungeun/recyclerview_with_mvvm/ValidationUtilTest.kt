package com.kyungeun.recyclerview_with_mvvm

import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateDrinkTest() {
        val drink = Drink(
            id = 1, name = "Margarita", alcoholic = "Alcoholic", category = "Ordinary Drink",
            image = "https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/5noda61589575158.jpg",
            info = "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt. Shake tequila, blue curacao, and lime juice with ice, strain into the salt-rimmed glass, and serve.",
            dateModified = "2015-08-18 14:42:59"
        )
        assertEquals(true, ValidationUtil.validateDrink(drink))
    }

    @Test
    fun validateDrinkEmptyTest() {
        val drink = Drink(
            id = null, name = "", alcoholic = "", category = "",
            image = "", info = "", dateModified = ""
        )
        assertEquals(false, ValidationUtil.validateDrink(drink))
    }

    @Test
    fun validateDrinkIdEmptyTest() {
        val drink = Drink(
            id = null, name = "Margarita", alcoholic = "Alcoholic", category = "Ordinary Drink",
            image = "https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/5noda61589575158.jpg",
            info = "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt. Shake tequila, blue curacao, and lime juice with ice, strain into the salt-rimmed glass, and serve.",
            dateModified = "2015-08-18 14:42:59"
        )
        assertEquals(false, ValidationUtil.validateDrink(drink))
    }

    @Test
    fun validateDrinkNameEmptyTest() {
        val drink = Drink(
            id = 1, name = "", alcoholic = "Alcoholic", category = "Ordinary Drink",
            image = "https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/5noda61589575158.jpg",
            info = "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt. Shake tequila, blue curacao, and lime juice with ice, strain into the salt-rimmed glass, and serve.",
            dateModified = "2015-08-18 14:42:59"
        )
        assertEquals(false, ValidationUtil.validateDrink(drink))
    }

    @Test
    fun validateDrinkImageEmptyTest() {
        val drink = Drink(
            id = 1, name = "Margarita", alcoholic = "Alcoholic", category = "Ordinary Drink",
            image = "",
            info = "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt. Shake tequila, blue curacao, and lime juice with ice, strain into the salt-rimmed glass, and serve.",
            dateModified = "2015-08-18 14:42:59"
        )
        assertEquals(false, ValidationUtil.validateDrink(drink))
    }
}
