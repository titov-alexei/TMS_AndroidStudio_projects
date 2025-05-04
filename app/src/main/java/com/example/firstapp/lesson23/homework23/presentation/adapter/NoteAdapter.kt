package com.example.firstapp.lesson23.homework23.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.NotesLayoutBinding
import com.example.firstapp.lesson23.homework23.domain.Notes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class NoteAdapter(
    private val onDeleteClick: (Long) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {

    private var items = emptyList<Notes>()

    private val formatDate = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("Europe/Minsk")
    }

    inner class ItemViewHolder(val binding: NotesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Notes) {
            binding.textNote.text = item.textNotes
            binding.dateNotes.text = item.dateNotes.toString()
            binding.deleteNote.setOnClickListener {
                onDeleteClick(item.id)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = NotesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = items[position]
        holder.onBind(note)
        holder.binding.dateNotes.text = formatDate.format(Date(note.dateNotes))
    }

    fun updateNotes(list: List<Notes>) {
        val diffUtilResult = DiffUtil.calculateDiff(NoteDiffUtilCallback(items, list))
        items = list.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }
}