package com.example.kkm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kkm.R
import com.example.kkm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}