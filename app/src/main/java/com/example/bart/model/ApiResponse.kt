package com.example.bart.model

data class ApiResponse(
    val root: Root
)

// Root object
data class Root(
    val id: String,
    val date: String,
    val time: String,
    val station: List<Station>,
    val message: String
)

// Station object
data class Station(
    val name: String,
    val abbr: String,
    val etd: List<Etd>
)

// ETD (Estimated Time of Departure) object
data class Etd(
    val destination: String,
    val abbreviation: String,
    val limited: String,
    val estimate: List<Estimate>
)

// Estimate object
data class Estimate(
    val minutes: String,
    val platform: String,
    val direction: String,
    val length: String,
    val color: String,
    val hexcolor: String,
    val bikeflag: String,
    val delay: String,
    val cancelflag: String,
    val dynamicflag: String
)
