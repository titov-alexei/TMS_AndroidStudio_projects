package com.example.firstapp.lesson22.multiAdapter

import androidx.recyclerview.widget.DiffUtil

class MultiDiffUtilCallback(val firstList: List<Elements>, val secondList: List<Elements>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = firstList.size

    override fun getNewListSize(): Int = secondList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = firstList[oldItemPosition]
        val newItem = secondList[newItemPosition]
        return oldItem::class == newItem::class && oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return firstList[oldItemPosition] == secondList[newItemPosition]
    }
}