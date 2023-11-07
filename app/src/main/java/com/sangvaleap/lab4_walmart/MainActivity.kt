package com.sangvaleap.lab4_walmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sangvaleap.lab4_walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userList = arrayListOf<User>(
        User("John", "Doe", "john.doe@test.com", "123"),
        User("Jane", "Doe", "jane.doe@test.com", "123"),
        User("Bob", "Smith", "bob.smith@test.com", "123"),
                User("John", "Smith", "john.smith@test.com", "123"),
    User("Nani", "Luis", "nani.lius@test.com", "123"),
    User("Kaka", "Ricardo", "kaka.r@test.com", "123")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password =  binding.etPassword.text.toString()
            val user = finUser(email, password)
            if(user != null){
                val intent = Intent(this, ShoppingCategoryActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Email or password is incorrect", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCreate.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.tvForgotPwd.setOnClickListener {
            val email = "kaka.r@test.com"
            val password = "123"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_TEXT, "your password is $password")
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }else{
                Toast.makeText(this, "No app can handle this intent", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun finUser(email: String, password: String): User?{
        return userList.find{u->u.username==email && u.password == password}
    }
}