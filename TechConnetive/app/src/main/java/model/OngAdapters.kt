package model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import techconnective.herokuapp.com.R

class OngAdapters(var context: Context, var arrayList: ArrayList<ListOng>) :
    RecyclerView.Adapter<OngAdapters.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var listOng:ListOng = arrayList.get(position)

        holder.icons.setImageResource(listOng.iconOng!!)
        holder.listOng.text = listOng.listOng

        holder.listOng.setOnClickListener{
            Toast.makeText(context, listOng.listOng, Toast.LENGTH_SHORT).show()
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var icons = itemView.findViewById<ImageView>(R.id.icon_image_view)
        var listOng = itemView.findViewById<TextView>(R.id.title_text_view)
    }
}