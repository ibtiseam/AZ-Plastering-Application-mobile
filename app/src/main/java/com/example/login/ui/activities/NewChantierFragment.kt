package com.example.login.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R

//
class NewChantierFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_new_chantier, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewChantierFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}