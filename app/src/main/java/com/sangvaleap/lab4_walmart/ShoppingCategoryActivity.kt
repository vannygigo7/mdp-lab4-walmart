package com.sangvaleap.lab4_walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.setMargins
import com.sangvaleap.lab4_walmart.databinding.ActivityShoppingCategoryBinding

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    private val categories = listOf<Category>(
        Category("Electronic", R.drawable.electronic),
        Category("Clothing", R.drawable.clothing),
        Category("Clothing", R.drawable.cosmetic),
        Category("Food", R.drawable.food),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getSerializableExtra("user") as? User
        println(user)
        if(user != null){
            setWelcomeUser(user)
        }
        createGridLayout()
    }

    private fun createGridLayout(){
        for (c in categories){
            binding.gridCategory.addView(getCategoryTile(c))
        }
    }

    private fun getCategoryTile(category: Category):LinearLayout{
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        var layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10))
        linearLayout.layoutParams = layoutParams;
        linearLayout.gravity = android.view.Gravity.CENTER

        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(
            dpToPx(150),
            dpToPx(150)
        )
        imageView.setImageResource(category.image) // Make sure to replace with your actual image resource

        val textView = TextView(this)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.text = category.name
        textView.textSize = 18.0F

        linearLayout.addView(imageView)
        linearLayout.addView(textView)
        linearLayout.setOnClickListener{
            Toast.makeText(this, "You have chosen the ${category.name} category of shopping", Toast.LENGTH_SHORT).show()
        }
        return linearLayout
    }

    private fun setWelcomeUser(user: User){
        binding.tvWelcome.text = "Welcome ${user.username}"
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

}
