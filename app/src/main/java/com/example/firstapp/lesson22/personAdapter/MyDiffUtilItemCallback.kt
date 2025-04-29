package com.example.firstapp.lesson22.personAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.firstapp.databinding.StudentLayoutBinding

class MyDiffUtilItemCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}

class MyListAdapter : ListAdapter<Person, RecyclerViewAdapter.ItemViewHolder>(MyDiffUtilItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ItemViewHolder {
        val binding = StudentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewAdapter.ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ItemViewHolder, position: Int) {
        holder.onBind(
            getItem(position),
            position = position,
            onClick = { clickedPosition ->
                val updatedPerson = currentList[clickedPosition].copy(rating = 0.01)
                submitList(currentList.toMutableList().apply {
                    set(clickedPosition, updatedPerson)
                })
            }

        )
    }
}