package com.marcelo.nybooks.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.repository.BooksApiDataSource
import com.marcelo.nybooks.ui.adapter.BooksListAdapter
import com.marcelo.nybooks.ui.base.BaseActivity
import com.marcelo.nybooks.ui.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.app_name)
        setupViewModel()
    }

    private fun setupViewModel() {
        //val viewModel:  BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
            .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, {
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

        viewModel.viewFlipperLiveData.observe(this, {
            it?.let {viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let {msgErrorId ->
                    textViewShowError.text = getString(msgErrorId)
                }
            }

        })

        viewModel.getBooks()
    }
}