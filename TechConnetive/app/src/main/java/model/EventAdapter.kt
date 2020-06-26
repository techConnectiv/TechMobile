package model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.menu.HomeFragment

class EventAdapter(var arrayList: ArrayList<ListEvent>, var clickListener: HomeFragment) :
    RecyclerView.Adapter<EventAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.event_list, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.initialize(arrayList.get(position), clickListener)

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons = itemView.findViewById<ImageView>(R.id.txt_icon)
        var listEvent = itemView.findViewById<TextView>(R.id.txt_user)
        var data = itemView.findViewById<TextView>(R.id.txt_data)
        var hora = itemView.findViewById<TextView>(R.id.txt_hora)
        var cardView = itemView.findViewById<CardView>(R.id.eventClickEvents)

        fun initialize(item: ListEvent, action: OnClickEvent) {

            icons.setImageResource(item.iconOng!!)
            listEvent.text = item.listEvent
            data.text = item.data
            //hora.text = item.hora

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)

                fadein(cardView)
            }
        }

        private fun fadein(view: View) {
            val animation = AlphaAnimation(0f, 1f)
            animation.duration = 300L
            animation.repeatMode = Animation.REVERSE
            animation.repeatCount = 0
            view.startAnimation(animation)
        }

    }

    interface OnClickEvent {
        fun onItemClick(arrayList: ListEvent, position: Int)
    }

}