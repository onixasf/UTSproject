package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Mie Goreng", "Mie goreng dengan udang dan sayuran", R.drawable.mie_goreng),
            Food("Nasi Goreng", "Nasi goreng spesial", R.drawable.nasigoreng),
            Food("Black Salad", "Salad sehat dengan dressing khas", R.drawable.black_salad),
            Food("Batagor", "Bakso tahu goreng dengan sambal kacang", R.drawable.batagor),
            Food("Cireng", "Camilan goreng dari tepung tapioka", R.drawable.cireng),
            Food("Cheese Cake", "Kue keju yang lembut", R.drawable.cheesecake),
            Food("Donat", "Donat manis dengan berbagai topping", R.drawable.donut),
            Food("Cappuccino", "Kopi cappuccino yang creamy", R.drawable.cappuchino),
            Food("Kopi Hitam", "Kopi hitam yang kuat", R.drawable.kopi_hitam),
            Food("Sparkling Tea", "Teh berkarbonasi segar", R.drawable.sparkling_tea)
        )

        adapter = FoodAdapter(foodList)
        recyclerView.adapter = adapter

        val btnStartOrder: Button = findViewById(R.id.btnStartOrder)
        btnStartOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putParcelableArrayListExtra("FOOD_LIST", ArrayList(foodList))
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}