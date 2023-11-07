package com.sangvaleap.lab4_walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sangvaleap.lab4_walmart.databinding.ActivityShoppingCategoryBinding

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("user") as? User
        println(user)
        if(user != null){
            setWelcome(user)
        }
    }

    private fun setWelcome(user: User){
        binding.tvWelcome.text = "Welcome ${user.username}"
    }


}
