package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class FragmentA : Fragment() {

    private lateinit var btnNextFragment: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNextFragment = view.findViewById(R.id.btnNextFragment)
        btnNextFragment.setOnClickListener {
            navigationToFragmentB()
        }

        //Задача №4.
        val tempStr = arguments?.getString(DATA_FRAGMENT) ?: getString(R.string.fragment_a)
        view.findViewById<TextView>(R.id.tvFragmentA).text = tempStr

    }

    //Задача 3. Замена одного фрагмента на другой
    private fun navigationToFragmentB() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerL19, FragmentB())
            .commit()
    }

    //Задача №4
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