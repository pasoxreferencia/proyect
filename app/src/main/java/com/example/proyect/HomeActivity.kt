package com.example.proyect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //recycler
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val pacientes = ArrayList<Paciente>()

        //Aquí iría n los datos leídos de Firestore
       /* pacientes.add(Paciente("Ayla", age = 0, R.drawable.foto1))
        pacientes.add(Paciente("Martin", age = 5, R.drawable.foto2))
        pacientes.add(Paciente("Belen", age = 39, R.drawable.foto3))*/

        val adapter = AdapterPaciente(pacientes)

        recyclerView.adapter = adapter


        //setup
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