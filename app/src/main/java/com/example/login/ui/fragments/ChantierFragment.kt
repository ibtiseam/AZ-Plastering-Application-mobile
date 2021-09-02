package com.example.login.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.login.chantierApi.RetrofitService
import com.example.login.data.repositories.ChantierRepository
import com.example.login.databinding.FragmentChantierBinding
import com.example.login.ui.activities.DetailActivity
import com.example.recycleview.adapter.ChantierAdapter
import com.example.recycleview.viewmodels.ChantierViewModel
import com.example.recycleview.viewmodels.MyViewModelFactory
import java.io.ByteArrayOutputStream

//
class ChantierFragment : Fragment() {

    private val TAG = "MainActivity"

    lateinit var viewModel: ChantierViewModel

    private var _binding: FragmentChantierBinding? = null
    private val binding get() = _binding!!

    private val retrofitService = RetrofitService.getInstance()
    val adapter = ChantierAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentChantierBinding.inflate(inflater, container, false)


        /****************GET CHANTIER****************/

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(ChantierRepository(retrofitService))).get(
                ChantierViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

        viewModel.chantierList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setChantierList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllChantier()


        /****************FIN GET CHANTIER****************/

        /****************DETAILS****************/
        var chantiers = viewModel.chantierList


        adapter.setOnItemClickListener(object : ChantierAdapter.onItemClickListener {
            override fun onIemClick(position: Int) {
                val intent = Intent(context, DetailActivity::class.java)

                val name = chantiers.value?.get(position)?.name
                val client = chantiers.value?.get(position)?.client
                val region = chantiers.value?.get(position)?.region
                val type_projet = chantiers.value?.get(position)?.type_projet
                val type_travaux = chantiers.value?.get(position)?.type_travaux


                val filePath = chantiers.value?.get(position)?.filePath
                val filePath2 = chantiers.value?.get(position)?.filePath2
                val filePath3 = chantiers.value?.get(position)?.filePath3
                val filePath4 = chantiers.value?.get(position)?.filePath4
                val filePath5 = chantiers.value?.get(position)?.filePath5

//                val imageBytes2 = Base64.decode(filePath2, Base64.DEFAULT)
//                val bitmap2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
//                val stream = ByteArrayOutputStream()
//                bitmap2.compress(Bitmap.CompressFormat.JPEG,100, stream)

                intent.putExtra("name", name)
                intent.putExtra("region", region)
                intent.putExtra("type_travaux", type_travaux)
                intent.putExtra("client", client)
                intent.putExtra("type_projet", type_projet)

                intent.putExtra("filePath", filePath)

//                intent.putExtra("filePath2", bitmap2)

//                intent.putExtra("filePath3", filePath3)

                intent.putExtra("filePath4", filePath4)


//                intent.putExtra("filePath5", filePath5)

                context?.startActivity(intent)

            }
        })


        /****************END DETAILS****************/

        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            ChantierFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}