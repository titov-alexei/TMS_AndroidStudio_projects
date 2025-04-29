package com.example.firstapp.lesson22.personAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.StudentLayoutBinding

class RecyclerViewAdapter(private var items: MutableList<Person>):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: StudentLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Person, position: Int, onClick: (Int) -> Unit) {
            binding.nameTextView.text = item.name
            binding.ratingTextView.text = item.rating.toString()
            binding.ratingTextView.setOnClickListener {
                onClick.invoke(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = StudentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(items[position], position) { position ->
            items[position].rating += 0.01
            notifyItemChanged(position)
        }
    }

    fun update(list: List<Person>) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(items, list))
        items = list.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged() //Если использовать вместо diffUtilResult (1 и 3 строчка) - не целесообразно при больших данных
    }


}