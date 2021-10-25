package com.marcelo.nybooks.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.ui.adapter.BooksListAdapter
import com.marcelo.nybooks.ui.base.BaseActivity
import com.marcelo.nybooks.ui.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksActivity : BaseActivity() {

    private val viewModel: BooksViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.app_name)
        setupViewModel()
    }

    private fun setupViewModel() {
        //val viewModel:  BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, {setRecycler->
            setRecycler?.let { books ->
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

        viewModel.viewFlipperLiveData.observe(this, {pairs->
            pairs?.let {viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let {msgErrorId ->
                    textViewShowError.text = getString(msgErrorId)
                }
            }

        })

        viewModel.getBooks()
    }
}