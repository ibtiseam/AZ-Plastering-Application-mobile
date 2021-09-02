package com.example.recycleview.adapter


import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.databinding.AdapterChantierBinding
import com.example.recycleview.Chantier
import com.example.recycleview.adapter.ChantierAdapter.onItemClickListener


class ChantierAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private lateinit var mLitener: onItemClickListener

    private val TAG = "MainActivity"

    interface onItemClickListener {
        fun onIemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mLitener = listener
    }


    var chantiers = mutableListOf<Chantier>()

    fun setChantierList(chantiers: List<Chantier>) {
        this.chantiers = chantiers.toMutableList()
        notifyDataSetChanged()
    }
    fun getChantierList() : List<Chantier> {
        return chantiers

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterChantierBinding.inflate(inflater, parent, false)



        return MainViewHolder(binding,mLitener)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val chantier = chantiers[position]
        holder.binding.name.text = chantier.name

        val imageBytes = Base64.decode(chantier.filePath, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
       // imageView.setImageBitmap(decodedImage)

        Glide.with(holder.itemView.context).load(decodedImage).into(holder.binding.imageview)


    }

    override fun getItemCount(): Int {
        return chantiers.size
    }
}

class MainViewHolder(val binding: AdapterChantierBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            listener.onIemClick(adapterPosition)
        }
    }
}