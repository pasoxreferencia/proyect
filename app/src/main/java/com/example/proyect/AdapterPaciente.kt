package com.example.proyect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyect.databinding.ContentItemBinding



class AdapterPaciente(var list: ArrayList<Paciente>, val listener:(Paciente)->Unit) : RecyclerView.Adapter<AdapterPaciente.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var title: TextView
        var count: TextView

        init {
            title=view.findViewById(R.id.txtTitle)
            count=view.findViewById(R.id.txtCount)
        }
        override fun onClick(v: View?) {
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)

        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text= list[position].name
        holder.count.text=list[position].age

        val item= list[position]
        holder.itemView.setOnClickListener{
            listener(item)
        }
    }
    override fun getItemCount(): Int {

        return list.size
    }
}
