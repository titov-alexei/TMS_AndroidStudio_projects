package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentB : Fragment() {

    private lateinit var btnBackToFragment: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBackToFragment = view.findViewById(R.id.btnBackFragment)
        btnBackToFragment.setOnClickListener {
            backToFragment()
        }
    }

    private fun backToFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerL19, FragmentA())
            .commit()
    }
}