package com.example.login.ui.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView.ScaleType
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.ActivityDetailBinding
import technolifestyle.com.imageslider.FlipperLayout
import technolifestyle.com.imageslider.FlipperView


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        val bundle: Bundle? = intent.extras

        val name = bundle!!.getString("name")
        val region = bundle!!.getString("region")
        val type_travaux = bundle!!.getString("type_travaux")
        val client = bundle!!.getString("client")
        val type_projet = bundle!!.getString("type_projet")


        val filePath = bundle!!.getString("filePath")

//        val filePath2 = bundle!!.getString("filePath2")
//        val filePath3 = bundle!!.getString("filePath3")
        val filePath4 = bundle!!.getString("filePath4")
//        val filePath5 = bundle!!.getString("filePath5")

        val imageBytes = Base64.decode(filePath, Base64.DEFAULT)
//        val imageBytes2 = Base64.decode(filePath2, Base64.DEFAULT)
//        val imageBytes3 = Base64.decode(filePath3, Base64.DEFAULT)
        val imageBytes4 = Base64.decode(filePath4, Base64.DEFAULT)
//        val imageBytes5 = Base64.decode(filePath5, Base64.DEFAULT)


        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//        val bitmap2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
//        val bitmap3 = BitmapFactory.decodeByteArray(imageBytes3, 0, imageBytes3.size)
        val bitmap4 = BitmapFactory.decodeByteArray(imageBytes4, 0, imageBytes4.size)
//        val bitmap5 = BitmapFactory.decodeByteArray(imageBytes5, 0, imageBytes5.size)

        addFlipperView(bitmap, binding.imageSliderChantier,name)
//        addFlipperView(bitmap2, binding.imageSliderChantier)
//        addFlipperView(bitmap3, binding.imageSliderChantier)
        addFlipperView(bitmap4, binding.imageSliderChantier,name)
//        addFlipperView(bitmap5, binding.imageSliderChantier)


        binding.tvClient.text = client
        binding.tvRegion.text = region
        binding.tvTraveaux.text = type_travaux
        binding.tvDetails.text = type_projet

        // Glide.with(this).load(url).into(binding.image)
        // binding.itemImage1.setImageResource(url)


        setContentView(binding.root)
    }


    fun addFlipperView(bitmap: Bitmap?, flipperLayout: FlipperLayout?, name: String?) {
        getViewFlipper(bitmap)?.let {
            flipperLayout?.scrollTimeInSec = 5
            flipperLayout?.addFlipperView(it)
            name?.let { it1 -> it.setDescription(it1) }
            it.setDescriptionBackgroundColor(Color.GRAY)

        }

    }

    fun getViewFlipper(bitmap: Bitmap?): FlipperView? {
        bitmap?.let {
            val view = FlipperView(baseContext)
            view.setImageScaleType(ScaleType.CENTER_CROP)
                .setDescription("Description")
                .setImageBitmap(bitmap) { imageView, image ->
                    imageView.setImageBitmap(image as Bitmap)
                }

            return view
        }
        return null
    }


}