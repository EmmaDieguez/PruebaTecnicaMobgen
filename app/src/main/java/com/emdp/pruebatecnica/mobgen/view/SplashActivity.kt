package com.emdp.pruebatecnica.mobgen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.emdp.pruebatecnica.mobgen.model.api.CategoriesResponse
import com.emdp.pruebatecnica.mobgen.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel
    private var categories: List<CategoriesResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.getCategories().observe(this, { catRespList ->
            categories = catRespList

            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}