package model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import techconnective.herokuapp.com.R

class DonateAdapters(var context: Context, var arrayList: ArrayList<ListDonate>) :
    RecyclerView.Adapter<DonateAdapters.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.donate_list, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var listEvent:ListDonate = arrayList.get(position)

        //holder.icons.setImageResource(listEvent.iconOng!!)
        holder.nomeOng.text = listEvent.nomeOng
        holder.data.text = listEvent.data
        holder.hora.text = listEvent.hora

        holder.cardView.setOnClickListener{

            fadein(holder.cardView)

            Toast.makeText(context, listEvent.nomeOng, Toast.LENGTH_SHORT).show()
        }
    }
    private fun fadein(view: View){
        val animation = AlphaAnimation(0f,1f)
        animation.duration = 300L
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = 0
        view.startAnimation(animation)
    }


    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //var icons = itemView.findViewById<ImageView>(R.id.txt_icon)
        var nomeOng = itemView.findViewById<TextView>(R.id.txt_user)
        var data = itemView.findViewById<TextView>(R.id.txt_data)
        var hora = itemView.findViewById<TextView>(R.id.txt_hora)
        var cardView = itemView.findViewById<CardView>(R.id.eventClick)

    }
}