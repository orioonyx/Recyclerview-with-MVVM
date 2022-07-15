package com.kyungeun.recyclerview_with_mvvm.ui.drinkdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kyungeun.recyclerview_with_mvvm.databinding.DrinkDetailFragmentBinding
import com.kyungeun.recyclerview_with_mvvm.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DrinkDetailFragment : Fragment() {

    private var binding: DrinkDetailFragmentBinding by autoCleared()
    private val viewModel: DrinkDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DrinkDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }


    }


//
//    private fun bindCharacter(character: Character) {
//        binding.storeName.text = character.name
//        binding.productName.text = character.species
//        binding.price.text = character.status
//        binding.infoText.text = character.gender
//        Glide.with(binding.root)
//            .load(character.image)
//            .override(512, 512)
//            .error(R.drawable.empty)
//            .into(binding.image)
//    }
}
