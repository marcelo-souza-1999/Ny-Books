package com.marcelo.nybooks.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcelo.nybooks.network.model.Books

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Books>> = MutableLiveData()

    fun getBooks() {
        booksLiveData.value = createFakeBooks()
    }

    private fun createFakeBooks(): List<Books> {
        return listOf(
            Books("Titulo Um", "Autor um"),
            Books("Titulo Dois", "Autor dois"),
            Books("Titulo Tres", "Autor tres"),
        )
    }
}