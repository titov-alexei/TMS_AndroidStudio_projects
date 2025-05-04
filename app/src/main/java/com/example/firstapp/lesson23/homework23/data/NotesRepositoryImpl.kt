package com.example.firstapp.lesson23.homework23.data

import com.example.firstapp.lesson23.homework23.domain.Notes
import com.example.firstapp.lesson23.homework23.domain.NotesRepository

class NotesRepositoryImpl : NotesRepository {
    private val notes = mutableListOf<Notes>()

    override suspend fun getNotes(): List<Notes> = notes

    override suspend fun addNote(note: Notes) {
        notes.add(note)
    }

    override suspend fun deleteNote(id: Long) {
        notes.removeAll { it.id == id }
    }
}