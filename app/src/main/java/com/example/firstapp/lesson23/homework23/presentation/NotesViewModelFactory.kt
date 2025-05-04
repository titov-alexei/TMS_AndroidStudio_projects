package com.example.firstapp.lesson23.homework23.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstapp.lesson23.homework23.domain.NotesRepository
import com.example.firstapp.lesson23.homework23.domain.usecase.AddNoteUseCase
import com.example.firstapp.lesson23.homework23.domain.usecase.DeleteNoteUseCase

class NotesViewModelFactory(
    private val repository: NotesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(
            addNoteUseCase = AddNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        ) as T
    }
}