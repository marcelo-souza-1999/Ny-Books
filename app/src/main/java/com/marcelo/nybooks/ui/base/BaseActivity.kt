package com.marcelo.nybooks.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int, showButtonBack: Boolean = false) {
        toolbar.title = getString(titleIdRes)

        setSupportActionBar(toolbar)
        if (showButtonBack){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}