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

class OngAdapters(var arrayList: ArrayList<ListOng>, var clickListener: OnClickOng) :
    RecyclerView.Adapter<OngAdapters.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.initialize(arrayList.get(position), clickListener)

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons = itemView.findViewById<ImageView>(R.id.icon_image_view)
        var listOng = itemView.findViewById<TextView>(R.id.title_text_view)
        var cardView = itemView.findViewById<CardView>(R.id.eventClickDonate)

        fun initialize(item: ListOng, action: OnClickOng) {

            icons.setImageResource(item.iconOng!!)
            listOng.text = item.listOng

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

    interface OnClickOng {
        fun onItemClick(arrayList: ListOng, position: Int)
    }
}