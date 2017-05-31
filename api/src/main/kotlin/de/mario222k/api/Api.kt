package de.mario222k.api

import com.google.gson.Gson
import de.mario222k.api.service.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Api {
    companion object {
        val instance: Api by lazy { Api() }
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    val service: Service = retrofit.create(Service::class.java)
}