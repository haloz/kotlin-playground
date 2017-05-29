package de.mario222k.api

data class Show(
        val id: Long,
        val name: String,
        val type: String?,
        val language: String?,
        val genres: List<String>,
        val runtime: Int,
        val schedule: Schedule,
        val image: Image,
        val summary: String?)


data class Episode(
        val id: Long,
        val name: String,
        val season: Int,
        val number: Int,
        val airdate: String,
        val airtime: String,
        val runtime: Int,
        val image: String?,
        val summary: String?,
        val show: Show)

data class Schedule(
        val time: String,
        val days: List<String>
)

data class Image(
        val original: String?,
        val medium: String?
)