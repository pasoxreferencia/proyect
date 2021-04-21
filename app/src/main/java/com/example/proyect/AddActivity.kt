package com.example.proyect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyect.databinding.ActivityAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var binding: ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")

        action(email = email?: "")

    }
    private val db = FirebaseFirestore.getInstance()
    private fun action (email: String) {

        title = getString(R.string.gestionar_citas)

        binding.emailTextView.text = email

        binding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed() //vuelve a la activity anterior
        }

        binding.saveButton.setOnClickListener {

            val nombre= binding.nameTextView.text.toString() //le paso el nombre del paciente al nombre del documento

            db.collection("users").document(email).collection("pacientes").document(nombre).set(
                hashMapOf(

                     "nombre" to binding.nameTextView.text.toString(),
                     "edad" to binding.edadTextView.text.toString(),
                     "cita" to binding.citaTextView.text.toString(),
                     "hospital" to binding.hospitalTextView.text.toString(),
                     "cita2" to binding.cita2TextView.text.toString(),
                     "hospital2" to binding.hospital2TextView.text.toString()


                )
            )
                        //vaciar los textView
                        binding.nameTextView.setText("")
                        binding.edadTextView.setText("")
                        binding.citaTextView.setText("")
                        binding.hospitalTextView.setText("")
                        binding.cita2TextView.setText("")
                        binding.hospital2TextView.setText("")

        }
        binding.getButton.setOnClickListener {
            val nombre= binding.nameTextView.text.toString() //le paso el nombre del paciente al nombre del documento
            val TAG = getString(R.string.mensajes)
            
            
                db.collection("users")
                        .document(email).collection("pacientes")
                        .document(nombre)
                        .get().addOnSuccessListener {


                            binding.nameTextView.setText(it.get("nombre") as String?)
                            binding.edadTextView.setText(it.get("edad") as String?)
                            binding.citaTextView.setText(it.get("cita") as String?)
                            binding.hospitalTextView.setText(it.get("hospital") as String?)
                            binding.cita2TextView.setText(it.get("cita2") as String?)
                            binding.hospital2TextView.setText(it.get("hospital2") as String?)

                        }
                        }



        binding.deleteButton.setOnClickListener {
            val nombre= binding.nameTextView.text.toString() //le paso el nombre del paciente al nombre del documento

            db.collection("users")
                .document(email)
                .collection("pacientes")
                .document(nombre).delete()
        }
    }

}

