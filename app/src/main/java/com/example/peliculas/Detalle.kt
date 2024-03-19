package com.example.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Detalle : AppCompatActivity() {

    val database = Firebase.database
    val myRef = database.getReference("peliculas")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)


        var nombre = findViewById<EditText>(R.id.nombreP)
        var genero = findViewById<EditText>(R.id.generoP)
        var año = findViewById<EditText>(R.id.añoP)
        var imagen = findViewById<ImageView>(R.id.imagenP)
        var editar = findViewById<Button>(R.id.editarP)
        var eliminar = findViewById<Button>(R.id.eliminarP)

        val parametro = intent.extras

        nombre.setText(parametro?.getCharSequence("nombre").toString())
        genero.setText(parametro?.getCharSequence("genero").toString())
        año.setText(parametro?.getCharSequence("año").toString())

        if(parametro?.getCharSequence("genero") == "Terror"){
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.terror))

        }
        else if(parametro?.getCharSequence("genero") == "Comedia")
        {
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.comedy))
        }
        else{
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.perfil))
        }

        editar.setOnClickListener {
            var pelicula = PeliCampos(nombre.text.toString(), genero.text.toString(), año.text.toString())
            myRef.child(parametro?.getCharSequence("id").toString()).setValue(pelicula).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Exito pelicula editada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Error" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        eliminar.setOnClickListener {

            var builder: AlertDialog.Builder = MaterialAlertDialogBuilder(this)

            builder.setMessage("¿Estas seguro de eliminar esta pelicula?")
                .setPositiveButton("Aceptar") { dialog, id ->

                    myRef.child(parametro?.getCharSequence("id").toString()).removeValue()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Exito pelicula eliminada", Toast.LENGTH_LONG)
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Error" + task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }

                .setNegativeButton("Cancelar"){
                    dialog, id ->

                }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }
}