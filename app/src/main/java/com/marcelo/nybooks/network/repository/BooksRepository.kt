package com.marcelo.nybooks.network.repository

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}