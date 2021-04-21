package com.example.proyect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyect.databinding.ContentItemBinding


class AdapterPaciente : RecyclerView.Adapter<AdapterPaciente.ViewHolder>() {

    val lista = mutableListOf<Paciente>()

    fun AdapterPaciente(lista: List<Paciente>) {
        this.lista.addAll(lista)
    }

    class ViewHolder(val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun enlazar(paciente: Paciente) {

            binding.txtTitle.text = paciente.name
            binding.txtCount.text = paciente.age

        }

        companion object {

            fun crear(parent: ViewGroup): ViewHolder {

                val inflater = LayoutInflater.from(parent.context)
                val binding = ContentItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPaciente.ViewHolder {
        return ViewHolder.crear(parent)
    }

    override fun onBindViewHolder(holder: AdapterPaciente.ViewHolder, position: Int) {
        holder.enlazar(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}




/*
private lateinit var binding: ContentItemBinding
class AdapterPaciente(var list: ArrayList<Paciente>): RecyclerView.Adapter<AdapterPaciente.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bindItems(data: Paciente) {

            val title: TextView = itemView.findViewById(R.id.txtTitle)
            val count: TextView = itemView.findViewById(R.id.txtCount)
            val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

            title.text = data.name
            count.text = data.age.toString()

  //          Glide.with(itemView.context).load(data.thumbnail).into(thumbnail)

            itemView.setOnClickListener {

                //Toast.makeText(itemView.context, "Perfil de ${data.name}", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.content_item,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterPaciente.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {

        return list.size
    }
}*/
