package com.example.otus_kotlin_coroutines

import kotlinx.serialization.*

@Serializable
data class GeoInfo(
    val ip: String,

    @SerialName("country_code")
    val countryCode: String,

    @SerialName("country_name")
    val countryName: String,

    @SerialName("region_code")
    val regionCode: String,

    @SerialName("region_name")
    val regionName: String,

    val city: String,

    @SerialName("zip_code")
    val zipCode: String,

    @SerialName("time_zone")
    val timeZone: String,

    val latitude: Double,
    val longitude: Double,

    @SerialName("metro_code")
    val metroCode: Long
)
