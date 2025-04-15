package com.example.firstapp.lesson19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentABinding


class FragmentA : Fragment() {

    private var navController : NavController? = null
    private var viewBinding: FragmentABinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentABinding.inflate(inflater,container,false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        setupView()

        //Задача №4.
        val tempStr = arguments?.getString(DATA_FRAGMENT) ?: getString(R.string.fragment_a)
        view.findViewById<TextView>(R.id.tvFragmentA).text = tempStr

    }

    private fun setupView() {
        viewBinding?.btnNextFragment?.setOnClickListener {
            navigationToFragmentB()
        }
    }

    private fun navigationToFragmentB() {
        /*requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerL19, FragmentB())
            .commit()*/
        //navController?.navigate(R.id.action_fragmentA_to_fragmentB)
    }

    companion object {
        const val DATA_FRAGMENT = "data_fragment"

        fun getInstance(str: String): FragmentA {
            return FragmentA().apply {
                arguments = Bundle().apply {
                    putString(DATA_FRAGMENT, str)
                }
            }
        }
    }
}