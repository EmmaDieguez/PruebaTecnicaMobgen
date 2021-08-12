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
import com.emdp.pruebatecnica.mobgen.listeners.OnBackPressedListener
import com.emdp.pruebatecnica.mobgen.view.adapters.HousesAdapter
import com.emdp.pruebatecnica.mobgen.viewModel.MainViewModel


class HousesFragment : Fragment(), OnBackPressedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_house_list, container, false)

        activity?.let {
            val mainViewModel = ViewModelProvider(it, defaultViewModelProviderFactory)
                .get(MainViewModel::class.java)

            mainViewModel.getAllHouses().observe(viewLifecycleOwner, { housesList ->
                housesList?.let {
                    // Set the adapter
                    if (view is RecyclerView) {
                        with(view) {
                            layoutManager = LinearLayoutManager(context)
                            adapter = HousesAdapter(it)
                        }
                    }
                }
            })
        }
        return view
    }

    override fun onBackPressed(): Boolean {
        return true
    }
}