package com.emdp.pruebatecnica.mobgen.view.adapters

import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.emdp.pruebatecnica.mobgen.common.Utils
import com.emdp.pruebatecnica.mobgen.databinding.AdapterBooksBinding
import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse

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

        utils.showHideViewItems(holder.tvName, holder.tvBookName, book.name)
        utils.showHideViewItems(holder.tvIsbn, holder.tvBookIsbn, book.isbn)
        utils.showHideViewItems(holder.tvAuthors, holder.llBooksAuthors, book.authors, context)
        utils.showHideViewItems(holder.tvPages, holder.tvBookPages, book.numberOfPages.toString())
        utils.showHideViewItems(holder.tvPublisher, holder.tvBookPublisher, book.publisher)
        utils.showHideViewItems(holder.tvCountry, holder.tvBookCountry, book.country)
        utils.showHideViewItems(holder.tvMediaType, holder.tvBookMediaType, book.mediaType)
        utils.showHideViewItems(holder.tvReleased, holder.tvBookReleased, utils.getDateFormat(book.released))
    }

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(binding: AdapterBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName: TextView = binding.tvName
        val tvIsbn: TextView = binding.tvIsbn
        val tvAuthors: TextView = binding.tvAuthors
        val tvPages: TextView = binding.tvPages
        val tvPublisher: TextView = binding.tvPublisher
        val tvCountry: TextView = binding.tvCountry
        val tvMediaType: TextView = binding.tvMediaType
        val tvReleased: TextView = binding.tvReleased

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