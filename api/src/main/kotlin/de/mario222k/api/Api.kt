package de.mario222k.api

import com.google.gson.Gson
import de.mario222k.api.model.Show
import de.mario222k.api.service.Service
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    private val service: Service = retrofit.create(Service::class.java)

    fun getShows(page: Int = 1): Observable<List<Show>> {
        return Observable.create { observer ->
            service.getShows(page).enqueue(object : Callback<List<Show>> {
                override fun onFailure(p0: Call<List<Show>>?, p1: Throwable?) {
                    observer.onError(p1)
                }

                override fun onResponse(p0: Call<List<Show>>?, p1: Response<List<Show>>?) {
                    observer.onNext(p1?.body() ?: emptyList())
                    observer.onComplete()
                }
            })
        }
    }
}