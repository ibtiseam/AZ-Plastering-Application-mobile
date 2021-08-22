package com.example.login.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.login.R


//
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home, container, false)

       val imageSlider =view.findViewById<ImageSlider>(R.id.image_slider)
        val imagelist =ArrayList<SlideModel>()

        imagelist.add(SlideModel("https://www.gplplasterers.co.uk/wp-content/uploads/2019/10/skimming-walls-ceilings.jpg","AZ-Plastering"))
        imagelist.add(SlideModel(R.drawable.service,"AZ-Plastering"))
        imagelist.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcP2gumAP_jIDq5Lyz356bTEXbgcn7jYDGsg&usqp=CAU","AZ-Plastering"))
        imagelist.add(SlideModel("https://st.depositphotos.com/1000291/3585/i/950/depositphotos_35855601-stock-photo-plasterer-at-indoor-ceiling-work.jpg","AZ-Plastering"))


        imageSlider.setImageList(imagelist,ScaleTypes.FIT)

        return view

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}