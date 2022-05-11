package com.example.myapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp.R
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.fragments.HistoryFragment
import com.example.myapp.fragments.HomeFragment
import com.example.myapp.fragments.ProfileFragment
import com.example.myapp.fragments.ShopFragment

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
        binding.container.setOnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
                    //hui
                    return@setOnItemSelectedListener true
                }
                R.id.shop -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ShopFragment()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.history -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment()).commit()
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
        setContentView(binding.root)
    }
}