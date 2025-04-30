package com.example.firstapp.lesson23.domain

import com.example.firstapp.lesson22.personAdapter.Person

interface FilterRepository {
    fun getPersons(): List<PersonEntity>
}