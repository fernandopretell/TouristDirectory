package com.fulbiopretell.touristdirectory.presentation.place_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fulbiopretell.touristdirectory.R
import com.fulbiopretell.touristdirectory.databinding.FragmentPlaceListBinding
import com.fulbiopretell.touristdirectory.util.Result
import com.fulbiopretell.touristdirectory.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

const val PLACE_ID = "PLACE_ID"

@AndroidEntryPoint
class PlaceListFragment : Fragment(R.layout.fragment_place_list) {

    private val viewModel: PlaceListViewModel by viewModels()
    private val binding by viewBinding(FragmentPlaceListBinding::bind)

    private val adapter: PlaceAdapter by lazy {
        PlaceAdapter { placeId ->
            val bundle = Bundle().apply {
                putString(PLACE_ID, placeId)
            }
            findNavController().navigate(R.id.action_placeListFragment_to_placeDetailFragment, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.places.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.pb.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pb.visibility = View.GONE
                    binding.recyclerView.adapter = adapter
                    adapter.updateData(result.data)
                }
                is Result.Error -> {
                    binding.pb.visibility = View.GONE
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}