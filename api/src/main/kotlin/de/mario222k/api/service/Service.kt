package de.mario222k.api.service

import de.mario222k.api.model.Episode
import de.mario222k.api.model.Show
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("shows")
    fun getShows(@Query("page") page: Int = 1): Call<List<Show>>

    @GET("shows/{id}")
    fun getShow(@Path("id") id: Long): Call<Show>

    @GET("shows/{id}/episodes")
    fun getEpisodes(@Path("id") showId: Long): Call<List<Episode>>
}