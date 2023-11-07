package com.sangvaleap.lab4_walmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sangvaleap.lab4_walmart.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            val fname = binding.etFname.text.toString()
            val lname = binding.etLname.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val user = User(fname, lname, email, password)
            if(!validateForm(user)){
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_LONG).show()
            }else{
                var intent = Intent(this, ShoppingCategoryActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
            println(user)
        }
    }

    private fun validateForm(user: User): Boolean{
        if(user.firstName.isEmpty() || user.lastName.isEmpty() || user.username.isEmpty() || user.password.isEmpty()){
            return  false
        }
        return true
    }
}