package com.example.firstapp.lesson23.data

import com.example.firstapp.lesson23.domain.FilterRepository
import com.example.firstapp.lesson23.domain.PersonEntity

class FilterRepositoryImpl: FilterRepository {
    override fun getPersons(): List<PersonEntity> {
        return listOf(
            PersonEntity("Alex", 0),
            PersonEntity("Boba", 1),
            PersonEntity("Ivan", 2),
            PersonEntity("Alix", 3)
        )
    }
}