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
import com.emdp.pruebatecnica.mobgen.model.api.CharactersResponse
import com.emdp.pruebatecnica.mobgen.view.adapters.CharactersAdapter
import com.emdp.pruebatecnica.mobgen.viewModel.MainViewModel


class CharactersFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var characters: MutableList<CharactersResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CharactersAdapter(characters)
            }
        }
        return view
    }
}