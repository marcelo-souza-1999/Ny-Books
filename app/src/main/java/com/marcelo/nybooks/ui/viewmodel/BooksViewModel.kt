package com.marcelo.nybooks.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.network.response.BooksBodyResponse
import com.marcelo.nybooks.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Books>> = MutableLiveData()

    fun getBooks() {
       ApiService.service.getBooks().enqueue(object: Callback<BooksBodyResponse?>
       {
           override fun onResponse(call: Call<BooksBodyResponse?>, response: Response<BooksBodyResponse?>)
           {
                if (response.isSuccessful){
                    val books: MutableList<Books> = mutableListOf()
                    response.body()?.let { booksBodyResponse ->
                        for (result in booksBodyResponse.booksResults)
                        {
                            val book = Books(
                                title = result.bookDetails[0].title,
                                author = result.bookDetails[0].author,
                                description = result.bookDetails[0].description
                            )
                            books.add(book)
                        }
                    }
                    booksLiveData.value = books
                }
           }

           override fun onFailure(call: Call<BooksBodyResponse?>, t: Throwable) {

           }
       })
    }
}


