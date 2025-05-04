package com.example.firstapp.lesson23.homework23.domain.usecase

import com.example.firstapp.lesson23.homework23.domain.NotesRepository

class DeleteNoteUseCase (
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Long) {
        repository.deleteNote(noteId)
    }
}