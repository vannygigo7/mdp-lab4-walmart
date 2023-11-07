package com.sangvaleap.lab4_walmart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sangvaleap.lab4_walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userList = arrayListOf(
        User("John", "Doe", "john.doe@test.com", "123"),
        User("Jane", "Doe", "jane.doe@test.com", "123"),
        User("Bob", "Smith", "bob.smith@test.com", "123"),
        User("Test", "User", "test@test.com", "123"),
        User("Nani", "Luis", "nani.lius@test.com", "123"),
        User("Kaka", "Ricardo", "kaka.r@test.com", "123")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("user") as? User
        println(user)
        if(user != null){
            addUser(user)
        }

        binding.btnSignin.setOnClickListener{onSignIn()}

        binding.btnCreate.setOnClickListener{onCreate()}

        binding.tvForgotPwd.setOnClickListener { onForgotPwd() }
    }

    private fun onCreate(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun onForgotPwd(){
        val email = binding.etEmail.text.toString()
        if (email.isEmpty()){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return
        }

        val user = findUserByEmail(email)
        if(user == null){
            Toast.makeText(this, "No user with this email address", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, user.username)
        intent.putExtra(Intent.EXTRA_TEXT, "your password is ${user.password}")
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }else{
            Toast.makeText(this, "No app can handle this intent", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSignIn(){
        val email = binding.etEmail.text.toString()
        val password =  binding.etPassword.text.toString()
        if(!validateForm(email, password)){
            return
        }

        val user = findUserByEmail(email)
        if(user != null && validateUser(user, email, password)){
            val intent = Intent(this, ShoppingCategoryActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Email or password is incorrect", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateForm(email: String, password: String): Boolean{
        if(email.isEmpty()){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return false
        }else if(password.isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun findUserByEmail(email: String): User?{
        return userList.find{u->u.username==email}
    }

    private fun validateUser(user: User, email: String, password: String): Boolean{
        return user.username == email && user.password == password
    }

    private fun addUser(user: User){
        userList.add(user)
        println(userList)
    }
}