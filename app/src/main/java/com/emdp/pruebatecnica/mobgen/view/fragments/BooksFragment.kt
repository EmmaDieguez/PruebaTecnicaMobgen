package com.emdp.pruebatecnica.mobgen.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.emdp.pruebatecnica.mobgen.R
import com.emdp.pruebatecnica.mobgen.model.api.BooksResponse
import com.emdp.pruebatecnica.mobgen.view.adapters.BooksAdapter
import com.emdp.pruebatecnica.mobgen.viewModel.MainViewModel


class BooksFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var books: MutableList<BooksResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getAllBooks().observe(this, {
            books = it as MutableList<BooksResponse>
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_books_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = BooksAdapter(books)
            }
        }
        return view
    }
}