package de.mario222k.kotlintv

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import de.mario222k.api.Api
import de.mario222k.api.Show
import kotlinx.android.synthetic.main.activity_shows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowsActivity : AppCompatActivity() {

    val api = Api.instance.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shows)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            refresh()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_shows, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when(item.itemId) {
            R.id.action_refresh -> {
                refresh()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun refresh() {
        api.getShows(1).enqueue(object: Callback<List<Show>> {
            override fun onResponse(p0: Call<List<Show>>?, p1: Response<List<Show>>?) {
                Snackbar.make(fab, "loaded shows: ${p1?.body()?.size ?: -1}", Snackbar.LENGTH_SHORT).show()
            }

            override fun onFailure(p0: Call<List<Show>>?, p1: Throwable?) {
                Snackbar.make(fab, "error: ${p1?.message}", Snackbar.LENGTH_SHORT).show()
            }

        })
    }
}
