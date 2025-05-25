package com.example.firstapp.lesson28.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.FactLayoutBinding
import com.example.firstapp.lesson28.domain.ArtFact

class NetworkAdapter(
    private val facts: List<ArtFact>,
    private val onItemClick: (id: String) -> Unit
) : RecyclerView.Adapter<NetworkAdapter.FactViewHolder>() {

    inner class FactViewHolder(val binding: FactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ArtFact) {
            binding.fact.text = item.title

            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = FactLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
       holder.onBind(facts[position])
    }

    override fun getItemCount() = facts.size
}