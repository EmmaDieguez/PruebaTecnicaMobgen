package com.emdp.pruebatecnica.mobgen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emdp.pruebatecnica.mobgen.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_PruebaTecnicaMobgen)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}