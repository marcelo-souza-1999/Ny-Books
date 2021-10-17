package com.marcelo.nybooks.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.ui.adapter.BooksListAdapter
import com.marcelo.nybooks.ui.base.BaseActivity
import com.marcelo.nybooks.ui.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.title_toolbar)
        setupViewModel()
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
                    adapter = BooksListAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.getBooks()

        //val viewModel:  BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
    }
}