package com.example.firstapp.lesson23.homework23.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.firstapp.lesson23.homework23.domain.Notes

class NoteDiffUtilCallback(private val firstList: List<Notes>, private val secondList: List<Notes>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = firstList.size

    override fun getNewListSize(): Int = secondList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return firstList[oldItemPosition].id == secondList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return firstList[oldItemPosition] == secondList[newItemPosition]
    }
}