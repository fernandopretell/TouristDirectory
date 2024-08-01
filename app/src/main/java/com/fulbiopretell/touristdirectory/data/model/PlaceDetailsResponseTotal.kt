package com.fulbiopretell.touristdirectory.data.model

data class PlaceDetailsResponseTotal(
    val result: PlaceDetailResponse
)

data class PlaceDetailResponse(
    val address_components: List<AddressComponent>,
    val adr_address: String,
    val business_status: String,
    val current_opening_hours: OpeningHours?,
    val editorial_summary: EditorialSummary?,
    val formatted_address: String,
    val formatted_phone_number: String?,
    val geometry: Geometry,
    val icon: String,
    val icon_background_color: String,
    val icon_mask_base_uri: String,
    val international_phone_number: String?,
    val name: String,
    val opening_hours: OpeningHours?,
    val photos: List<Photo>?,
    val place_id: String,
    val plus_code: PlusCode,
    val rating: Double,
    val reference: String,
    val reviews: List<Review>?,
    val types: List<String>,
    val url: String,
    val user_ratings_total: Int,
    val utc_offset: Int,
    val vicinity: String,
    val wheelchair_accessible_entrance: Boolean
)

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)

data class OpeningHours(
    val open_now: Boolean,
    val periods: List<Period>?,
    val weekday_text: List<String>
)

data class Period(
    val close: TimeInfo?,
    val open: TimeInfo
)

data class TimeInfo(
    val date: String?,
    val day: Int,
    val time: String,
    val truncated: Boolean?
)

data class EditorialSummary(
    val language: String,
    val overview: String
)

data class PlusCode(
    val compound_code: String,
    val global_code: String
)

data class Review(
    val author_name: String,
    val author_url: String,
    val language: String,
    val original_language: String,
    val profile_photo_url: String,
    val rating: Int,
    val relative_time_description: String,
    val text: String,
    val time: Long,
    val translated: Boolean
)
