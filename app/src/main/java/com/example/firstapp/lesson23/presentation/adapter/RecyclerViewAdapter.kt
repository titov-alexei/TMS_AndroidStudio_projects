package com.example.firstapp.lesson23.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.StudentLayoutBinding
import com.example.firstapp.lesson22.personAdapter.DiffUtilCallback
import com.example.firstapp.lesson22.personAdapter.Person

class RecyclerViewAdapter(private var items: MutableList<Person>):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: StudentLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Person) {
            binding.nameTextView.text = item.name
            binding.ratingTextView.text = item.rating.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = StudentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun update(list: List<Person>) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(items, list))
        items = list.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }


}