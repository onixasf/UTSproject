package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lat.pam.utsproject.ListFoodActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btnLogin)

        // Username dan password yang telah ditentukan
        val validUsername = "Onixa"
        val validPassword = "admin1234"

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username == validUsername && password == validPassword) {
                // Login berhasil
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                // Pindah ke Activity lain jika perlu
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
            } else {
                // Login gagal
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}