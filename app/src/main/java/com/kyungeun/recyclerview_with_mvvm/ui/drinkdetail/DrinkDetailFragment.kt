package com.kyungeun.recyclerview_with_mvvm.ui.drinkdetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.kyungeun.recyclerview_with_mvvm.R
import com.kyungeun.recyclerview_with_mvvm.data.entities.Drink
import com.kyungeun.recyclerview_with_mvvm.databinding.DrinkDetailFragmentBinding
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import com.kyungeun.recyclerview_with_mvvm.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailFragment : Fragment() {

    private lateinit var layoutListener: ViewTreeObserver.OnGlobalLayoutListener

    private var binding: DrinkDetailFragmentBinding by autoCleared()

    private val viewModel: DrinkDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DrinkDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.drink.observe(
            viewLifecycleOwner
        ) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { _ ->
                        bindDrinkView(it.data.results[0])
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility = View.INVISIBLE
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.drinkView.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun bindDrinkView(drink: Drink) {
        binding.category.text = drink.category
        binding.alcoholic.text = drink.alcoholic
        binding.name.text = drink.name
        binding.info.text = drink.info

        layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            // Set the signature to be the last modified time of the image file.
            val imageMetadata = drink.dateModified?.let { ObjectKey(it) } ?: ObjectKey("")

            Glide.with(binding.root)
                .load(drink.image)
                .dontAnimate()
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .signature(imageMetadata)
                .into(object : CustomTarget<Drawable>(binding.image.width, 1) {
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        binding.drinkView.visibility = View.VISIBLE
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // clear resources
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        binding.image.setImageDrawable(resource)

                        binding.drinkView.visibility = View.VISIBLE

                        // Remove the listener
                        binding.image.viewTreeObserver.removeOnGlobalLayoutListener(layoutListener)
                    }

                })
        }
        binding.image.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
    }
}
