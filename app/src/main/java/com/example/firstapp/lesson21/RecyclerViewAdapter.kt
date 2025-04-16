package com.example.firstapp.lesson21

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ItemNameBinding

class RecyclerViewAdapter(
    private val list: MutableList<String>
): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Нажата ${position + 1} позиция", Toast.LENGTH_SHORT).show()
        }
    }

    fun addItem(text: String) {
        list.add(text)
        notifyItemInserted(list.size - 1)
    }

    fun clearItem() {
        list.clear()
        notifyDataSetChanged()
    }
}