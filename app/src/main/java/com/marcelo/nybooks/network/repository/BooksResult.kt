package com.marcelo.nybooks.network.repository

import com.marcelo.nybooks.network.model.Books

sealed class BooksResult {
    class Success(val books: List<Books>) : BooksResult()
    class ApiError(val statusCode: Int) : BooksResult()
    object ServerError : BooksResult()
}
