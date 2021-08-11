package com.emdp.pruebatecnica.mobgen.view.adapters

import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.emdp.pruebatecnica.mobgen.R
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.databinding.AdapterBooksBinding
import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse
import kotlin.coroutines.coroutineContext

class BooksAdapter(
    private val context: Context,
    private val books: List<BooksResponse>
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private val utils : Utils.Companion = Utils

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        holder.tvBookName.text = book.name
        holder.tvBookIsbn.text = book.isbn

        holder.llBooksAuthors.removeAllViews()

        book.authors.forEach { author ->
            val tvAuthor = TextView(context)
            tvAuthor.setTextColor(
                ContextCompat.getColor(context, R.color.primary_light)
            )
            tvAuthor.textSize = 16.0F
            tvAuthor.text = author
            holder.llBooksAuthors.addView(tvAuthor)
        }

        holder.tvBookPages.text = book.numberOfPages.toString()
        holder.tvBookPublisher.text = book.publisher
        holder.tvBookCountry.text = book.country
        holder.tvBookMediaType.text = book.mediaType
        holder.tvBookReleased.text = utils.getDate(book.released)
    }

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(binding: AdapterBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvBookName: TextView = binding.tvBookName
        val tvBookIsbn: TextView = binding.tvBookIsbn
        val llBooksAuthors: LinearLayout = binding.llBooksAuthors
        val tvBookPages: TextView = binding.tvBookPages
        val tvBookPublisher: TextView = binding.tvBookPublisher
        val tvBookCountry: TextView = binding.tvBookCountry
        val tvBookMediaType: TextView = binding.tvBookMediaType
        val tvBookReleased: TextView = binding.tvBookReleased

        override fun toString(): String {
            return super.toString() + " '" + tvBookIsbn.text + "'"
        }
    }

}