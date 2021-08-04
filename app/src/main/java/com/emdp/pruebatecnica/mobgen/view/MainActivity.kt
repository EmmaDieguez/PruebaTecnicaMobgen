package com.emdp.pruebatecnica.mobgen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emdp.pruebatecnica.mobgen.R
import com.emdp.pruebatecnica.mobgen.model.database.Categories

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories: MutableList<Categories>  = intent
            .getSerializableExtra("categories") as MutableList<Categories>
    }
}