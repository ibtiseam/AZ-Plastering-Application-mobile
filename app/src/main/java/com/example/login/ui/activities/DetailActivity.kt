package com.example.login.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.login.R
import com.example.login.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        val bundle: Bundle? = intent.extras

        val chantierIimagelist = ArrayList<SlideModel>()



        val title = bundle!!.getString("filename")
        val url = bundle!!.getString("image")

        chantierIimagelist.add(SlideModel(url, title))
        chantierIimagelist.add(SlideModel(url, title))
        chantierIimagelist.add(SlideModel(url, title))

        binding.imageSliderChantier?.setImageList(chantierIimagelist, ScaleTypes.FIT)

       // Glide.with(this).load(url).into(binding.image)
        // binding.itemImage1.setImageResource(url)

//        binding.tvChantier.text = title


        setContentView(binding.root)
    }
}