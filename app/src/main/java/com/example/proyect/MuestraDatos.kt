package com.example.proyect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyect.databinding.ActivityHomeBinding
import com.example.proyect.databinding.ActivityMuestraDatosBinding

class MuestraDatos : AppCompatActivity() {
    private lateinit var binding: ActivityMuestraDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMuestraDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val nombre = bundle?.getString("nombre")
        val edad = bundle?.getString("edad")
        val cita = bundle?.getString("cita")
        val hospital = bundle?.getString("hospital")
        val cita2 = bundle?.getString("cita2")
        val hospital2 = bundle?.getString("hospital2")
      title = nombre

    leer(nombre = nombre ?: "", cita = cita ?: "", hospital = hospital ?: "",
                cita2 = cita2 ?: "", hospital2 = hospital2 ?: "")
    }
    private fun leer(nombre: String, cita: String, hospital: String, cita2: String, hospital2: String) {
        binding.nombrervTextview.text = nombre
        binding.primeracita.text = cita
        binding.hospital.text = hospital
        binding.segundacita.text = cita2
        binding.hospital2.text = hospital2
    }
}