package com.marcelo.nybooks.network.repository

import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.network.response.BooksBodyResponse
import com.marcelo.nybooks.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
    {
        ApiService.serviceBooks().getBooks().enqueue(object : Callback<BooksBodyResponse?>
        {
            override fun onResponse(call: Call<BooksBodyResponse?>, response: Response<BooksBodyResponse?>)
            {
                when
                {
                    response.isSuccessful ->
                    {
                        val books: MutableList<Books> = mutableListOf()

                        response.body()?.let { booksBodyResponse ->
                            for (result in booksBodyResponse.booksResults)
                            {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }

                        booksResultCallback(BooksResult.Success(books))
                    }

                    else -> booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }
            override fun onFailure(call: Call<BooksBodyResponse?>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }
        })
    }
}