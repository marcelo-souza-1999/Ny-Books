package com.marcelo.nybooks.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.network.response.BooksBodyResponse
import com.marcelo.nybooks.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Books>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        ApiService.service.getBooks().enqueue(object : Callback<BooksBodyResponse?> {
            override fun onResponse(
                call: Call<BooksBodyResponse?>,
                response: Response<BooksBodyResponse?>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Books> = mutableListOf()
                        response.body()?.let { booksBodyResponse ->
                            for (result in booksBodyResponse.booksResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksLiveData.value = books
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                    }
                    response.code() == 401 -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    }
                    else -> {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                    }
                }
            }
            override fun onFailure(call: Call<BooksBodyResponse?>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}


