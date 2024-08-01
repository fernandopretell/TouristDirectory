package com.fulbiopretell.touristdirectory.presentation.place_detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.fulbiopretell.touristdirectory.R
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.databinding.FragmentPlaceDetailBinding
import com.fulbiopretell.touristdirectory.databinding.ItemReviewBinding
import com.fulbiopretell.touristdirectory.presentation.place_list.PLACE_ID
import com.fulbiopretell.touristdirectory.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.fulbiopretell.touristdirectory.util.Result

const val LAT = "LAT"
const val LON = "LON"

@AndroidEntryPoint
class PlaceDetailFragment : Fragment(R.layout.fragment_place_detail) {

    private val viewModel: PlaceDetailViewModel by viewModels()
    private val binding by viewBinding(FragmentPlaceDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeId = arguments?.getString(PLACE_ID) ?: return

        viewModel.place.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.pb.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pb.visibility = View.GONE
                    updateUI(result.data)
                }
                is Result.Error -> {
                    binding.pb.visibility = View.GONE
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        })

        viewModel.fetchPlaceById(placeId)
    }

    fun getStars(rating: Double): String {
        val filledStars = rating.toInt()
        val emptyStars = 5 - filledStars
        return "⭐".repeat(filledStars) + "☆".repeat(emptyStars)
    }

    @SuppressLint("StringFormatMatches")
    private fun updateUI(place: PlaceDetail) {
        binding.placeName.text = place.title
        binding.placeAddress.text = place.address
        binding.placeRating.text = getStars(place.rating)
        binding.placeOpenNow.text = getOpeningNowText(place.openingNow, requireContext())
        binding.placeTotalRatings.text = getString(R.string.total_ratings, place.userRatingsTotal.toInt())

        place.imageUrl.let {
            Glide.with(this).load(it).into(binding.placeImage)
        }

        binding.btnViewMap.setOnClickListener {
            val bundle = Bundle().apply {
                putDouble(LAT, place.latitude)
                putDouble(LON, place.longitude)
            }
            findNavController().navigate(R.id.action_placeDetailFragment_to_mapDialogFragment, bundle)
        }

        binding.reviewsContainer.removeAllViews()
        place.reviews.forEach { review ->
            val reviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(context), binding.reviewsContainer, false)
            reviewBinding.authorName.text = review.authorName
            reviewBinding.reviewText.text = review.text
            reviewBinding.reviewRating.text = getStars(review.rating.toDouble())
            reviewBinding.relativeTimeDescription.text = review.relativeTimeDescription
            Glide.with(this).load(review.profilePhotoUrl).into(reviewBinding.profilePhoto)
            binding.reviewsContainer.addView(reviewBinding.root)
        }
    }

    fun getOpeningNowText(openNow: Boolean, context: Context): SpannableString {
        val text = if (openNow) "Abierto ahora" else "Cerrado ahora"
        val color = if (openNow) R.color.open_now else R.color.closed_now
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            0, text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }
}
