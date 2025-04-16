package com.example.firstapp.lesson21

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ItemNameBinding

class ViewHolder(private val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String){
        binding.textView.text = item
    }
}