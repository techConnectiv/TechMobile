package model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import techconnective.herokuapp.com.R
import techconnective.herokuapp.com.configuracoes.SettingsDonateFragment

class DonateAdapters(var arrayList: ArrayList<ListDonate>, var clickListener: SettingsDonateFragment) :
    RecyclerView.Adapter<DonateAdapters.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.donate_list, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.initialize(arrayList.get(position), clickListener)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //var icons = itemView.findViewById<ImageView>(R.id.txt_icon)
        var nomeOng = itemView.findViewById<TextView>(R.id.txt_user_donate)
        var data = itemView.findViewById<TextView>(R.id.txt_data_donate)
        var hora = itemView.findViewById<TextView>(R.id.txt_hora_donate)
        var cardView = itemView.findViewById<CardView>(R.id.eventClickHistoric)

        fun initialize(item: ListDonate, action: OnClickDonate) {

            //icons.setImageResource(item.iconOng!!)
            nomeOng.text = item.nomeOng
            data.text = item.data
            hora.text = item.hora

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)

                fadein(cardView)
            }
        }
        private fun fadein(view: View){
            val animation = AlphaAnimation(0f,1f)
            animation.duration = 300L
            animation.repeatMode = Animation.REVERSE
            animation.repeatCount = 0
            view.startAnimation(animation)
        }

    }

    interface OnClickDonate {
        fun onItemClick(arrayList: ListDonate, position: Int)
    }
}