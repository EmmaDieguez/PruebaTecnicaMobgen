package com.emdp.pruebatecnica.mobgen.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.emdp.pruebatecnica.mobgen.databinding.AdapterBooksBinding
import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse

class BooksAdapter(
    private val books: List<BooksResponse>
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AdapterBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.idView.text = book.name
        holder.contentView.text = book.isbn
    }

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(binding: AdapterBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}