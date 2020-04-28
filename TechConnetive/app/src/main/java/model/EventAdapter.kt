package model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.event_list.view.*
import techconnective.herokuapp.com.R

class EventAdapter(private val events: List<Events>, val onClick: (Events) -> Unit) :
    RecyclerView.Adapter<EventAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list, parent, false)

        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events[position])
    }

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(events: Events) {
            with(itemView) {
                itemView.txt_user.text = events.nomeOng
                itemView.txt_hora.text = events.hora
                itemView.txt_data.text = events.data

                setOnClickListener {
                    onClick.invoke(events)
                }
            }
        }
    }
}