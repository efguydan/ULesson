package com.efedaniel.ulesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.efedaniel.ulesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}
