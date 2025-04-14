package com.example.firstapp.lesson20

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstapp.lesson19.FragmentA
import com.example.firstapp.lesson19.FragmentB


class MyPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private val fragments = mutableListOf<Fragment>(FragmentFirst(), FragmentSecond())

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }

    fun removeFragment(position: Int) {
        if (position < fragments.size) {
            fragments.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    //Возвращает кол-во страниц
    override fun getItemCount(): Int = fragments.size

    //Возвращает фрагмент для каждой страницы
    /*override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentFirst()
            1 -> FragmentSecond()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }*/
    override fun createFragment(position: Int): Fragment = fragments[position]
}
