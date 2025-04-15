package com.example.firstapp.lesson19

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val strForHome = arguments?.getString(HomeFragment.SOME_DATA)
        binding?.textViewHome?.text = strForHome
        binding?.btnGoToSettingsFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_settingsFragment)
        }

        binding?.btnBackToHomeFragment?.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                HomeFragment.BACK_DATA, binding?.editText2?.text.toString()
            )
            findNavController().popBackStack()
        }
    }


}