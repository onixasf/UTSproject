@file:Suppress("DEPRECATION")

package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Menerima foodList dari Intent
        foodList = intent.getParcelableArrayListExtra<Food>("FOOD_LIST") ?: emptyList()

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Filter hanya makanan yang dipilih
        val selectedFoodList = foodList.filter { it.isSelected }

        // Buat tampilan untuk setiap makanan yang dipilih
        val layout = findViewById<LinearLayout>(R.id.selectedFoodLayout)

        selectedFoodList.forEach { food ->
            val foodView = layoutInflater.inflate(R.layout.item_selected_food, layout, false)
            val foodName = foodView.findViewById<TextView>(R.id.tvFoodName)
            val tvQuantity = foodView.findViewById<TextView>(R.id.tvQuantity)
            val btnIncrease = foodView.findViewById<Button>(R.id.btnIncrease)
            val btnDecrease = foodView.findViewById<Button>(R.id.btnDecrease)

            foodName.text = food.name
            var quantity = 1
            tvQuantity.text = quantity.toString()

            btnIncrease.setOnClickListener {
                quantity++
                tvQuantity.text = quantity.toString()
            }

            btnDecrease.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    tvQuantity.text = quantity.toString()
                }
            }

            layout.addView(foodView)
        }

        // Tombol untuk memproses pesanan
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        btnOrder.setOnClickListener {
            if (selectedFoodList.isEmpty()) {
                Toast.makeText(this, "No food selected", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedFoodOrders = mutableListOf<Pair<String, Int>>()

            for (i in 0 until layout.childCount) {
                val foodView = layout.getChildAt(i)
                val foodName = foodView.findViewById<TextView>(R.id.tvFoodName).text.toString()
                val quantity = foodView.findViewById<TextView>(R.id.tvQuantity).text.toString().toInt()
                updatedFoodOrders.add(Pair(foodName, quantity))
            }

            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("ORDERING_NAME", findViewById<EditText>(R.id.etName).text.toString())
            intent.putExtra("ADDITIONAL_NOTES", findViewById<EditText>(R.id.etNotes).text.toString())

            // Mengumpulkan string untuk dikirim
            val foodNamesAndServings = updatedFoodOrders.map { "${it.first}: ${it.second}" }
            intent.putStringArrayListExtra("SELECTED_FOODS", ArrayList(foodNamesAndServings))
            startActivity(intent)
        }
    }
}