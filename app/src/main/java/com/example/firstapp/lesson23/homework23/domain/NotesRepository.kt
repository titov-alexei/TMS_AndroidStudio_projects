package com.example.firstapp.lesson23.homework23.domain

interface NotesRepository {
    suspend fun getNotes(): List<Notes>
    suspend fun addNote(note: Notes)
    suspend fun deleteNote(id: Long)

}