package com.marcelo.nybooks.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.network.repository.BooksRepository
import com.marcelo.nybooks.network.repository.BooksResult
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyBooksViewModelTeste {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Books>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks get sucess then sets booksLiveData`() {
        val books = listOf(
            Books("Titulo teste um", "Autor teste um", "Descrição teste um")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get server error then sets booksLiveData`() {
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500))
    }

    @Test
    fun `when view model getBooks get api error 401 then sets booksLiveData`() {
        val resultServerApirror = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultServerApirror)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))
    }

    @Test
    fun `when view model getBooks get api error 400 then sets booksLiveData`() {
        val resultServerApirror = MockRepository(BooksResult.ApiError(400))
        viewModel = BooksViewModel(resultServerApirror)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400_generic))
    }
}

class MockRepository(private val result: BooksResult) : BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}