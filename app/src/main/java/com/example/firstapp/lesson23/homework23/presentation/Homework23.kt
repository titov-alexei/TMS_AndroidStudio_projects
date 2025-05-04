package com.example.firstapp.lesson23.homework23.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityHomework23Binding
import com.example.firstapp.lesson23.homework23.data.NotesRepositoryImpl
import com.example.firstapp.lesson23.homework23.presentation.adapter.NoteAdapter

class Homework23 : AppCompatActivity() {
    private lateinit var binding: ActivityHomework23Binding
    private var adapter: NoteAdapter? = null
    //Создаем viewModel
    private val viewModel by viewModels<NoteViewModel> {
        NotesViewModelFactory(NotesRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomework23Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Подписываемся на изменения списка
        viewModel.notes.observe(this, Observer { notes ->
            // Обновляем recyclerView с новыми данными
            adapter?.updateNotes(notes)
        })

        setupView()
    }

    private fun setupView() {
        with(binding) {
            adapter = NoteAdapter { id ->
                viewModel.deleteNote(id)
            }
            recyclerViewNote.adapter = adapter
            recyclerViewNote.layoutManager = LinearLayoutManager(this@Homework23)

            btnAddNote.setOnClickListener {
                if (binding.enterNote.text.isNotBlank()) {
                    viewModel.addNote(binding.enterNote.text.toString())
                    enterNote.text.clear()
                } else {
                    Toast.makeText(this@Homework23, R.string.no_data, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}