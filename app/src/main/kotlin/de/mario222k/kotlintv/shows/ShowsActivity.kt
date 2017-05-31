package de.mario222k.kotlintv.shows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import de.mario222k.api.Api
import de.mario222k.kotlintv.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.show_activity.*

class ShowsActivity : AppCompatActivity(), ShowAdapter.ShowAdapterDelegate{
    override fun endReached() {
        if(isLoading) {
            return
        }
        loadPage(2)
    }

    val api = Api.instance
    private var isLoading = false

    private val showAdapter : ShowAdapter by lazy { ShowAdapter(this).apply{
        delegate = this@ShowsActivity
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_activity)
        setSupportActionBar(toolbar)

        list.apply {
            layoutManager = LinearLayoutManager(this@ShowsActivity)
            adapter = showAdapter
        }

        // TODO #5 support paging

        // TODO #6 add new activity for episode list (nearly the same as this activity / adapter)
        // TODO #7 add new activity for episode details

        loadPage()
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
        when (item.itemId) {
            R.id.action_refresh -> {
                loadPage()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadPage( page: Int = 1 ) {
        // TODO show loading indicator
        Observable.create<Boolean> {
            it.onNext(true)

            api.getShows(page).subscribe({ shows ->
                showAdapter.shows = shows
                it.onNext(false)
                it.onComplete()
            }, { _ ->
                it.onNext(false)
                it.onComplete()
            })

        }.subscribe { isLoading = it }
    }
}

