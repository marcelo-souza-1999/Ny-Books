package com.marcelo.nybooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.nybooks.R
import com.marcelo.nybooks.network.model.Books
import kotlinx.android.synthetic.main.items_books.view.*

class BooksListAdapter(
    private val books: List<Books>,
    private val onItemClickListener: ((book: Books) -> Unit)
) : RecyclerView.Adapter<BooksListAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_books, parent, false)
        return BooksViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount(): Int = books.size

    class BooksViewHolder(
        itemView: View,
        private val onItemClickListener: ((book: Books) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.txtTitle
        private val author = itemView.txtAuthor

        fun bindView(book: Books) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onItemClickListener(book)
            }
        }
    }
}