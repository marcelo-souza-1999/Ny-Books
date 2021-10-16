package com.marcelo.nybooks.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcelo.nybooks.R

class BooksActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
    }
}