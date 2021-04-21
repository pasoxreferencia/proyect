package com.example.proyect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyect.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType {
    CORREO
    //SE SEGUIRÁN AÑADIENDO PROVEEDORES
}

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val lista = ArrayList<Paciente>()
        //bucle para leer de la BBDD y alimentar recycler
        val db = FirebaseFirestore.getInstance()
        db.collection(email.toString())
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("TAG", "${document.id} => ${document.data}")
                        var paciente = Paciente(
                                document.getString("Name").toString(),
                                document.getString("Edad").toString()
                        )
                        lista.add(paciente)

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents.", exception)
                }

                lista.forEach{
                    Log.d("TAG", "${it.age}  ${it.name}}")
                }

        //recycler
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val adapter = AdapterPaciente()

        recyclerView.adapter = adapter


        //setup

        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {

        title = "Pacientes"

        binding.emailTextView.text = email
        //binding.providerTextView.text = provider //quitar

        binding.logOutButton.setOnClickListener {


            //actualizar BBDD
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