package com.example.firstapp.lesson23.homework23.domain.usecase

import com.example.firstapp.lesson23.homework23.domain.Notes
import com.example.firstapp.lesson23.homework23.domain.NotesRepository

class AddNoteUseCase (val repository: NotesRepository) {
    suspend operator fun invoke(text: String) {
        val note = Notes(
            id = System.currentTimeMillis(),
            textNotes = text,
            dateNotes = System.currentTimeMillis()
        )
        repository.addNote(note)
    }
}