package com.example.firstapp.lesson23.homework23.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson23.homework23.domain.Notes
import com.example.firstapp.lesson23.homework23.domain.usecase.AddNoteUseCase
import com.example.firstapp.lesson23.homework23.domain.usecase.DeleteNoteUseCase
import kotlinx.coroutines.launch

class NoteViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {

    private val _notes = MutableLiveData<List<Notes>>()
    val notes: LiveData<List<Notes>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _notes.value = addNoteUseCase.repository.getNotes()
        }
    }

    fun addNote(text: String) {
        viewModelScope.launch {
            addNoteUseCase(text)
            loadNotes()
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            deleteNoteUseCase(id)
            loadNotes()
        }
    }
}