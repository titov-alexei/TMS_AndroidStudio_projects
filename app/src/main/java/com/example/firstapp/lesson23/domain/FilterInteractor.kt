package com.example.firstapp.lesson23.domain

class FilterInteractor(val filterRepository: FilterRepository) {

    fun getFilteredAndPersons(text: String): List<PersonEntity> {
        return filterRepository.getPersons().filter {
            it.name.contains(text)
        }
    }
}