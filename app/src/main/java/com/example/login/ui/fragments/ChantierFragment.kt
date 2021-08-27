package com.example.login.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.login.data.network.RetrofitService
import com.example.login.data.repositories.ChantierRepository
import com.example.login.databinding.FragmentChantierBinding
import com.example.login.ui.activities.DetailActivity
import com.example.recycleview.adapter.ChantierAdapter
import com.example.recycleview.viewmodels.ChantierViewModel
import com.example.recycleview.viewmodels.MyViewModelFactory

//
class ChantierFragment : Fragment() {

    private val TAG = "MainActivity"

    lateinit var viewModel: ChantierViewModel

    private var _binding:FragmentChantierBinding? = null
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
                var title = chantiers.value?.get(position)?.filename
                var image = chantiers.value?.get(position)?.url


                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("filename", title)
                intent.putExtra("image",image)
                requireActivity()?.startActivity(intent)

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