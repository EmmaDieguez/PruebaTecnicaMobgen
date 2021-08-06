package com.emdp.pruebatecnica.mobgen.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.emdp.pruebatecnica.mobgen.viewModel.SplashViewModel
import java.io.Serializable

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.getCategories().observe(this, { categoriesList ->
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("categories", categoriesList as Serializable)
            }
            startActivity(intent)
            finish()
        })
    }
}