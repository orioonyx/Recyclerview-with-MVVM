package com.kyungeun.recyclerview_with_mvvm.ui.drinks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyungeun.recyclerview_with_mvvm.R
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.databinding.ItemDrinkBinding

class DrinksAdapter(private val listener: DrinkItemListener) : RecyclerView.Adapter<DrinkViewHolder>() {

    interface DrinkItemListener {
        fun onClickedDrink(drinkId: Int)
    }

    private val items = ArrayList<Drink>()

    fun setItems(items: ArrayList<Drink>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding: ItemDrinkBinding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) = holder.bind(items[position])
}

class DrinkViewHolder(private val itemBinding: ItemDrinkBinding, private val listener: DrinksAdapter.DrinkItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: Drink

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Drink) {
        this.user = item
        itemBinding.category.text = item.category
        itemBinding.alcoholic.text = item.alcoholic
        itemBinding.name.text = item.name


        Glide.with(itemBinding.root)
            .load(item.image)
            .override(512, 512)
            .error(R.drawable.empty)
            .into(itemBinding.image)

    }

    override fun onClick(v: View?) {
        listener.onClickedDrink(user.id)
    }
}

