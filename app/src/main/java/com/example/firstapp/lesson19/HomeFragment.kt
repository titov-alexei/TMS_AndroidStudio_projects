package com.example.firstapp.lesson19

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var navController: NavController? = null

    companion object {
        const val SOME_DATA = "someData"
        const val BACK_DATA = "backData"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<String>(BACK_DATA)
            ?.observe(viewLifecycleOwner) { result ->
                binding?.textView?.text = result

                // Очистка поля после получения данных
                findNavController().currentBackStackEntry?.savedStateHandle
                    ?.remove<String>(BACK_DATA)
            }

        binding?.btnGoToDetailsFragment?.setOnClickListener {
            val str = binding?.editTextHome?.text.toString()
            val bundle = bundleOf(SOME_DATA to str)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }



}