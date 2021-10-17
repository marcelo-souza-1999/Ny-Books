package com.marcelo.nybooks.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.model.Books
import com.marcelo.nybooks.ui.adapter.BooksListAdapter
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar();
        setupAdapter();
    }

    private fun setupToolbar() {
        toolbarMain.title = getString(R.string.title_toolbar)
        setSupportActionBar(toolbarMain)
    }

    private fun setupAdapter() {
        with(recyclerBooks)
        {
            layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = BooksListAdapter(getBooks())
        }
    }

    private fun getBooks(): List<Books> {
        return listOf(
            Books("Titulo Um", "Autor um"),
            Books("Titulo Dois", "Autor dois"),
            Books("Titulo Tres", "Autor tres"),
        )
    }
}