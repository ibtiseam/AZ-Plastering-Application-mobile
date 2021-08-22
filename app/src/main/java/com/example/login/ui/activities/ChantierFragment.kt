package com.example.login.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.data.network.RetrofitService
import com.example.login.data.repositories.MainRepository
import com.example.login.databinding.FragmentChantierBinding
import com.example.recycleview.adapter.MainAdapter
import com.example.recycleview.viewmodels.MainViewModel
import com.example.recycleview.viewmodels.MyViewModelFactory

//
class ChantierFragment : Fragment() {

    private val TAG = "MainActivity"

    lateinit var viewModel: MainViewModel

    private var _binding: FragmentChantierBinding? = null
    private val binding get() = _binding!!

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChantierBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllMovies()
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