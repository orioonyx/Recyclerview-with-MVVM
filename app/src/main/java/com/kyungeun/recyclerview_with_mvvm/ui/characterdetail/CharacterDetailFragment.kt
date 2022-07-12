package com.kyungeun.recyclerview_with_mvvm.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.kyungeun.recyclerview_with_mvvm.R
import com.kyungeun.recyclerview_with_mvvm.data.entities.Character
import com.kyungeun.recyclerview_with_mvvm.databinding.CharacterDetailFragmentBinding
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import com.kyungeun.recyclerview_with_mvvm.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding: CharacterDetailFragmentBinding by autoCleared()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.productCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.productCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCharacter(character: Character) {
        binding.storeName.text = character.name
        binding.productName.text = character.species
        binding.price.text = character.status
        binding.infoText.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .override(512, 512)
            .error(R.drawable.empty)
            .into(binding.image)
    }
}
