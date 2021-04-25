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


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var lista: ArrayList<Paciente>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        lista = ArrayList()
        //bucle para leer de la BBDD y alimentar recycler
        val db = FirebaseFirestore.getInstance()
        db.collection(email.toString())
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("TAG", "${document.id} => ${document.data}")
                        var paciente = Paciente(
                                document.getString("nombre").toString(),
                                document.getString("edad").toString(),
                                document.getString("cita").toString(),
                                document.getString("hospital").toString(),
                                document.getString("cita2").toString(),
                                document.getString("hospital2").toString()
                        )
                        lista.add(paciente)
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents.", exception)
                }

        //recycler
        CreaRV(lista)

        add(email ?: "", provider ?: "")

        binding.actualizar.setOnClickListener {
            CreaRV(lista)
        }
    }

    private fun add(email: String, provider: String) {

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
            finish()
        }


    }

    fun CreaRV(lista: ArrayList<Paciente>) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var adapter = AdapterPaciente(lista) {

            lista.forEach {
                Log.d("DATOS Lista", "${it.age}  ${it.name}}")
            }

            val intent = Intent(this, MuestraDatos::class.java).apply {

                /*       putStringArrayListExtra("LISTA",lista as ArrayList<String>)
                    putExtra("LISTA",lista)

                lista.forEach{
                    putExtra("nombre${it}",it.name)
                }
                for (i in 0..lista.size){
                    putExtra("nombre${i}", lista[i])
                }
*/


//                //for (i in 0..15) {
                putExtra("nombre", lista[0].name)
                putExtra("edad", lista[0].age)
                putExtra("cita", lista[0].cita)
                putExtra("hospital", lista[0].hospital)
                putExtra("cita2", lista[0].cita2)
                putExtra("hospital2", lista[0].hospital2)

            }

            startActivity(intent)

        }
        recyclerView.adapter = adapter


    }
}