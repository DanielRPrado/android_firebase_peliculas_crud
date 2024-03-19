package com.example.peliculas

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContextCompat

class PeliAdapter(private val context: Activity, private val arrayList: ArrayList<Pelicula>)

    : ArrayAdapter<Pelicula>(context,R.layout.item, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item,null)

        view.findViewById<TextView>(R.id.nombre).text = arrayList[position].nombre
        view.findViewById<TextView>(R.id.año).text = arrayList[position].año
        view.findViewById<TextView>(R.id.genero).text = arrayList[position].genero


        if(arrayList[position].genero == "Terror"){
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.terror))

        }
        else if(arrayList[position].genero == "Comedia")
        {
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.comedy))
        }
        else{
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.perfil))
        }
        return view

    }
}