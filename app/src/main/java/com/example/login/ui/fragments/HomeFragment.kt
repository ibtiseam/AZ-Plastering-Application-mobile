package com.example.login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.login.R
import com.example.login.adapter.RecycleViewAdapterHome
import com.example.login.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecycleViewAdapterHome.ViewHolder>? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val imagelist = ArrayList<SlideModel>()

        imagelist.add(
            SlideModel(
                "https://www.gplplasterers.co.uk/wp-content/uploads/2019/10/skimming-walls-ceilings.jpg",
                "AZ-Plastering"
            )
        )
        imagelist.add(SlideModel(R.drawable.service, "AZ-Plastering"))
        imagelist.add(
            SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcP2gumAP_jIDq5Lyz356bTEXbgcn7jYDGsg&usqp=CAU",
                "AZ-Plastering"
            )
        )
        imagelist.add(
            SlideModel(
                "https://st.depositphotos.com/1000291/3585/i/950/depositphotos_35855601-stock-photo-plasterer-at-indoor-ceiling-work.jpg",
                "AZ-Plastering"
            )
        )


        binding.imageSlider?.setImageList(imagelist, ScaleTypes.FIT)


        layoutManager = LinearLayoutManager(activity?.baseContext)
        binding.rcHome?.layoutManager = layoutManager

        adapter = RecycleViewAdapterHome()
        binding.rcHome?.adapter = adapter

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}