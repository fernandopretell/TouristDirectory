package com.fulbiopretell.touristdirectory.presentation.place_list

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.databinding.ItemPlaceBinding


class PlaceAdapter(private val onPlaceClick: (String) -> Unit) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var places: List<Place> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
        holder.itemView.setOnClickListener {
            onPlaceClick(place.id)
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    fun updateData(newPlaces: List<Place>) {
        places = newPlaces
        notifyDataSetChanged()
    }

    class PlaceViewHolder(private val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.placeName.text = place.title
            binding.placeDescription.text = place.description
            try {
                Glide.with(binding.placeImage.context).load(place.imageUrl).into(binding.placeImage)
            } catch (e: Exception) {
                val bitmap = place.imageBase64?.let { base64ToBitmap(it) }
                binding.placeImage.setImageBitmap(bitmap)
            }
        }

        fun base64ToBitmap(base64Str: String): Bitmap {
            val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }
    }
}