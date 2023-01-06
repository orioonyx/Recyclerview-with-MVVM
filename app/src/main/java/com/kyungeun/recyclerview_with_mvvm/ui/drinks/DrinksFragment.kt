package com.kyungeun.recyclerview_with_mvvm.ui.drinks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kyungeun.recyclerview_with_mvvm.R
import com.kyungeun.recyclerview_with_mvvm.databinding.DrinksFragmentBinding
import com.kyungeun.recyclerview_with_mvvm.utils.Resource
import com.kyungeun.recyclerview_with_mvvm.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinksFragment : Fragment(), DrinksAdapter.DrinkItemListener {

    private var binding: DrinksFragmentBinding by autoCleared()
    private val viewModel: DrinksViewModel by viewModels()
    private lateinit var adapter: DrinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DrinksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = DrinksAdapter(this)
        binding.drinkRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.drinkRv.adapter = adapter
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun setupObservers() {
        viewModel.drinkList.observe(
            viewLifecycleOwner
        ) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    if (!it.data?.results.isNullOrEmpty()) adapter.setItems(ArrayList(it.data!!.results))
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onClickedDrink(drinkId: Int) {
        findNavController().navigate(
            R.id.action_drinksFragment_to_drinkDetailFragment,
            bundleOf("id" to drinkId)
        )
    }
}
