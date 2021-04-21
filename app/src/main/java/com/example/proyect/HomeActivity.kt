package com.example.proyect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyect.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    CORREO
    //SE SEGUIRÁN AÑADIENDO PROVEEDORES
}

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate (layoutInflater)
        setContentView(binding.root)
        

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup (email:String, provider: String) {

        title = "Pacientes"

        binding.emailTextView.text = email
        //binding.providerTextView.text = provider //quitar

        binding.logOutButton.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        binding.addButton.setOnClickListener {

            val AddIntent = Intent(this, AddActivity::class.java).apply {
                putExtra("email", email)

            }
            startActivity(AddIntent)
        }

    }
}