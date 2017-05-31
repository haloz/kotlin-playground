package de.mario222k.kotlintv.shows

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import de.mario222k.api.model.Show
import de.mario222k.kotlintv.R
import kotlinx.android.synthetic.main.show_item.view.*

/**
 * Created by Mario.Sorge on 31.05.17.
 */
class ShowAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var delegate: ShowAdapterDelegate? = null


    var shows = emptyList<Show>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    val inflater: LayoutInflater? = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val showViewHolder = holder as? ShowViewHolder
        val show = shows[position]
        showViewHolder?.update(show)

        if (position == itemCount - 2) {
            delegate?.endReached()
        }
    }

    override fun getItemCount() = shows.count()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ShowViewHolder(inflater?.inflate(R.layout.show_item, parent, false) ?: View(context))

    class ShowViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun update(show: Show) {
            view.title.text = show.name
            view.description.text = show.summary
            view.runtime.text = show.runtime.toString()
            Picasso.with(view.context)
                    .load(show.image.medium)
                    .error(android.R.drawable.ic_lock_idle_alarm)
                    .into(view.cover)
        }
    }

    interface ShowAdapterDelegate {
        fun endReached()
    }
}