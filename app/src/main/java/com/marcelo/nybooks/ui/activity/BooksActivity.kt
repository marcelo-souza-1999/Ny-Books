package com.marcelo.nybooks.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.ui.adapter.BooksListAdapter
import com.marcelo.nybooks.ui.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar()
        setupViewModel()
    }

    private fun setupToolbar() {
        toolbarMain.title = getString(R.string.title_toolbar)
        setSupportActionBar(toolbarMain)
    }

    private fun setupViewModel() {
        val viewModel: BooksViewModel by viewModels()
        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks)
                {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksListAdapter(books)
                }
            }
        })
        viewModel.getBooks()

        //val viewModel:  BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
    }
}