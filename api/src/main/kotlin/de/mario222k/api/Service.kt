package de.mario222k.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("shows")
    fun getShows(@Query("page") page: Int): Call<List<Show>>

    @GET("shows/{id}")
    fun getShow(@Path("id") id: Long): Call<Show>

    @GET("shows/{id}/episodes")
    fun getEpisodes(@Path("id") showId: Long): Call<List<Episode>>
}