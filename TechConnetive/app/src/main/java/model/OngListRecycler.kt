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

class OngListRecycler(var arrayList: MutableList<ListOng>, var clickListener: OnClickOngRecycler) :
    RecyclerView.Adapter<OngListRecycler.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_ong, parent, false)
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
        var nomeOng = itemView.findViewById<TextView>(R.id.tv_nome_ong)
        var desc = itemView.findViewById<TextView>(R.id.txt_desc)
        var km = itemView.findViewById<TextView>(R.id.txt_km)
        var cardView = itemView.findViewById<CardView>(R.id.ongClickEvents)

        fun initialize(item: ListOng, action: OnClickOngRecycler) {

            icons.setImageResource(item.iconOng!!)
            nomeOng.text = item.listOng
            desc.text = item.descricao
            km.text = item.km

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

    interface OnClickOngRecycler {
        fun onItemClick(arrayList: ListOng, position: Int)
    }
}