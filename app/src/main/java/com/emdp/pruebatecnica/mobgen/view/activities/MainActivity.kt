package com.emdp.pruebatecnica.mobgen.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emdp.pruebatecnica.mobgen.R
import com.emdp.pruebatecnica.mobgen.listeners.OnBackPressedListener
import com.emdp.pruebatecnica.mobgen.listeners.OnCategoryClickListener
import com.emdp.pruebatecnica.mobgen.model.database.Categories
import com.emdp.pruebatecnica.mobgen.view.adapters.CategoryAdapter
import com.emdp.pruebatecnica.mobgen.view.fragments.BooksFragment
import com.emdp.pruebatecnica.mobgen.view.fragments.CharactersFragment
import com.emdp.pruebatecnica.mobgen.view.fragments.HousesFragment
import com.emdp.pruebatecnica.mobgen.viewModel.MainViewModel


class MainActivity : AppCompatActivity(), OnCategoryClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var categories: MutableList<Categories>
    private lateinit var categoryAdapter: CategoryAdapter
    private var title: TextView? = null
    private var btnBack : Button? = null
    private var recyclerView: RecyclerView? = null
    private var flContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewElements()

        title?.text = "Categories"

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        categories = intent.getSerializableExtra("categories") as MutableList<Categories>
        createCategoriesAdapter(categories)
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.fl_container)

        (fragment as? OnBackPressedListener)?.onBackPressed()?.not()?.let {
            restoreActivity()
        }

        super.onBackPressed()
    }

    override fun onItemClicked(category: Categories) {
        var fragment : Fragment? = null
        var strTitle : String? = null

        recyclerView?.visibility = View.GONE
        flContainer?.visibility = View.VISIBLE
        btnBack?.visibility = View.VISIBLE

        when(category.categoryName) {
            "Books" -> {
                strTitle = getString(R.string.tv_books)
                fragment = BooksFragment()
            }
            "Houses" -> {
                strTitle = getString(R.string.tv_houses)
                fragment = HousesFragment()
            }
            "Characters" -> {
                strTitle = getString(R.string.tv_characters)
                fragment = CharactersFragment()
            }
        }

        if (fragment != null) {
            title?.text = strTitle
            newFragment(fragment)
        }
    }

    private fun newFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction
            .replace(R.id.fl_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun findViewElements() {
        title = findViewById(R.id.tv_title)
        btnBack = findViewById(R.id.btn_back)
        recyclerView = findViewById(R.id.recyclerView)
        flContainer = findViewById(R.id.fl_container)

        btnBack?.setOnClickListener {
            super.onBackPressed()
            restoreActivity()
        }
    }

    private fun restoreActivity() {
        title?.text = getString(R.string.tv_categories)

        flContainer?.visibility = View.GONE
        btnBack?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE

        mainViewModel.getAllCategories().observe(this, {
            categoryAdapter.refreshAdapter(it)
        })
    }

    private fun createCategoriesAdapter(categories: List<Categories>) {
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(baseContext)
        recyclerView?.layoutManager = layoutManager

        categoryAdapter = CategoryAdapter(categories)
        categoryAdapter.setOnClickListener(this)

        recyclerView?.adapter = categoryAdapter
    }
}