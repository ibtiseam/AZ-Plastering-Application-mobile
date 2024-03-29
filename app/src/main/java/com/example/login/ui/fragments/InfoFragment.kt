package com.example.login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login.R

//
class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            InfoFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}