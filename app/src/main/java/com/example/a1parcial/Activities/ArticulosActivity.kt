package com.example.a1parcial.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.a1parcial.R

class ArticulosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)

        val toolbar = findViewById<Toolbar>(R.id.tb_articulos)
        setSupportActionBar(toolbar)
    }

}
