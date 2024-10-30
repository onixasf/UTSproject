package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var textViewFoodNames: TextView
    private lateinit var textViewOrderingName: TextView
    private lateinit var textViewAdditionalNotes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Inisialisasi TextView
        textViewFoodNames = findViewById(R.id.tvFoodNames)
        textViewOrderingName = findViewById(R.id.tvOrderingName)
        textViewAdditionalNotes = findViewById(R.id.tvAdditionalNotes)

        // Menerima data dari Intent
        val selectedFoods = intent.getStringArrayListExtra("SELECTED_FOODS") ?: arrayListOf()
        val orderingName = intent.getStringExtra("ORDERING_NAME") ?: "Anonymous"
        val additionalNotes = intent.getStringExtra("ADDITIONAL_NOTES") ?: "None"

        // Menampilkan informasi
        textViewFoodNames.text = "Food Orders:\n${selectedFoods.joinToString("\n")}"
        textViewOrderingName.text = "Ordering Name: $orderingName"
        textViewAdditionalNotes.text = "Additional Notes: $additionalNotes"

        // Tombol kembali ke menu
        findViewById<Button>(R.id.backtoMenu).setOnClickListener {
            Toast.makeText(this, "Back To Menu", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ListFoodActivity::class.java) // Ganti dengan nama aktivitas menu Anda
            startActivity(intent)
            finish() // Menutup aktivitas ini sehingga tidak muncul di back stack
        }
    }
}