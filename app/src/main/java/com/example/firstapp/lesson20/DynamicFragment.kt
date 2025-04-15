package com.example.firstapp.lesson20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.firstapp.R

class DynamicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dynamic, container, false)
        val tabNumber = arguments?.getInt(TAB_NUMBER, 1) ?: 1
        val str = getString(R.string.dymamic_fragment) + " " + tabNumber
        view.findViewById<TextView>(R.id.tvDymamic).text = str
        return view
    }

    companion object {
        const val TAB_NUMBER = "tabNumber"
        fun newInstance(tabNumber: Int): DynamicFragment {
            val fragment = DynamicFragment()
            val args = Bundle()
            args.putInt(TAB_NUMBER, tabNumber)
            fragment.arguments = args
            return fragment
        }
    }
}