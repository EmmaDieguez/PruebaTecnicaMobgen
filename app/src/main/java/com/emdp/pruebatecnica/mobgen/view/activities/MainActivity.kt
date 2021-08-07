package com.emdp.pruebatecnica.mobgen.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emdp.pruebatecnica.mobgen.R
import com.emdp.pruebatecnica.mobgen.listeners.OnCategoryClickListener
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import com.emdp.pruebatecnica.mobgen.view.adapters.CategoryAdapter
import com.emdp.pruebatecnica.mobgen.viewModel.MainViewModel


class MainActivity : AppCompatActivity(), OnCategoryClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var categories: MutableList<Categories>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        categories = intent.getSerializableExtra("categories") as MutableList<Categories>

        val title: TextView = findViewById(R.id.tv_title)
        title.text = "GOT Categories"
    }

    override fun onStart() {
        super.onStart()

        if (null == categories) {
            mainViewModel.getAllCategories().observe(this, {
                createAdapter(it)
            })
        } else {
            createAdapter(categories)
        }
    }

    private fun createAdapter(categories: List<Categories>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(baseContext)
        recyclerView.layoutManager = layoutManager

        val categoryAdapter = CategoryAdapter(categories)
        categoryAdapter.setOnClickListener(this)
        recyclerView.adapter = categoryAdapter
    }

    override fun onItemClicked(category: Categories) {
        Toast.makeText(this, category.categoryName, Toast.LENGTH_LONG).show()
    }
}