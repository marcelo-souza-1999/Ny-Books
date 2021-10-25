package com.marcelo.nybooks.directory

import com.marcelo.nybooks.network.repository.BooksApiDataSource
import com.marcelo.nybooks.network.service.ApiService.serviceBooks
import com.marcelo.nybooks.ui.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {

    factory {
        BooksApiDataSource()
    }

    viewModel {
        BooksViewModel(get())
    }
}

val networkModule = module {
    factory {
        serviceBooks()
    }
}