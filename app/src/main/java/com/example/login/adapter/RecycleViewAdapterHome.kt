package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R

class RecycleViewAdapterHome : RecyclerView.Adapter<RecycleViewAdapterHome.ViewHolder>() {

    private val itemTitles = arrayOf(
        "Chantier Ihec", "Chantier Ihec",
        "Chantier Ihec", "Chantier Ihec", "Chantier Ihec"
    )
    private val itemDetails = arrayOf(
        "relève le défi ! Pour combler le besoin de l'IHEC (Institut des Hautes Etudes Commerciales) de Sousse, en salles de classe supplémentaire",
        "relève le défi ! Pour combler le besoin de l'IHEC (Institut des Hautes Etudes Commerciales) de Sousse, en salles de classe supplémentaire",
        "relève le défi ! Pour combler le besoin de l'IHEC (Institut des Hautes Etudes Commerciales) de Sousse, en salles de classe supplémentaire",
        "relève le défi ! Pour combler le besoin de l'IHEC (Institut des Hautes Etudes Commerciales) de Sousse, en salles de classe supplémentaire",
        "relève le défi ! Pour combler le besoin de l'IHEC (Institut des Hautes Etudes Commerciales) de Sousse, en salles de classe supplémentaire"
    )
    private val itemImges = intArrayOf(

        R.drawable.service,
        R.drawable.carousel,
        R.drawable.ihec1,
        R.drawable.ihec2,
        R.drawable.ihec3
    )


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView
        var textTitle: TextView
        var textDes: TextView

        init {
            image = itemView.findViewById(R.id.item_image1)
            textTitle = itemView.findViewById(R.id.tv_chantier)
            textDes = itemView.findViewById(R.id.tv_details)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = itemTitles[position]
        holder.textDes.text = itemDetails[position]
        holder.image.setImageResource(itemImges[position])

        holder.itemView.setOnClickListener { v: View ->
            Toast.makeText(v.context, "Clicked on this item", Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int {
        return itemTitles.size
    }
}