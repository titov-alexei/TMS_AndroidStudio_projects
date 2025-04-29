package com.example.firstapp.lesson22.personAdapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(val firstList: List<Person>, val secondList: List<Person>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = firstList.size

    override fun getNewListSize(): Int = secondList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return firstList[oldItemPosition].id == secondList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return firstList[oldItemPosition] == secondList[newItemPosition]
    }
}