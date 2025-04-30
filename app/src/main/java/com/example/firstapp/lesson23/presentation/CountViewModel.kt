package com.example.firstapp.lesson23.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapp.lesson22.personAdapter.Person
import com.example.firstapp.lesson23.data.FilterRepositoryImpl
import com.example.firstapp.lesson23.domain.FilterInteractor
import java.nio.file.DirectoryStream.Filter

class CountViewModel(): ViewModel() {
    //private var _count = MutableLiveData<Int>(0)
    private var _count = MutableLiveData<List<Person>>(listOf())
    val count get() = _count
    //private val filterModel = FilterModel()
    private val interactor = FilterInteractor(filterRepository = FilterRepositoryImpl())

    fun touchButton(text: String) {
        //_count.value = _count.value?.plus(1)
        /*val filterData = filterModel.getPersons().filter {
            it.name.contains(text)
        }
        */

        _count.value = interactor.getFilteredAndPersons(text).map {
            Person(it.name, it.id, it.rating)
        }

    }
}

class FilterModel() {
    /*private val persons = listOf(
        Person("Alex", 0),
        Person("Boba", 1),
        Person("Ivan", 2),
        Person("Alix", 3)
    )

    fun getPersons(): List<Person> {
        return persons
    }*/

}