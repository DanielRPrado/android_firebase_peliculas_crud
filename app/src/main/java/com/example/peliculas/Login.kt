package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.security.Principal

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        var btnLogin = findViewById<Button>(R.id.login)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)

        btnLogin.setOnClickListener{

            if (email.text.toString() != "" && password.text.toString() != ""){
                auth.signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener{
                        task ->

                    if(task.isSuccessful){
                        Toast.makeText(this, "Se inicio sesión correctamente", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MenuPrincipal::class.java))

                    }
                    else{
                        Toast.makeText(this, "ERROR " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()

                    }
                }
            }
            else{
                Toast.makeText(this, "Introduce Email o contraseña ", Toast.LENGTH_LONG).show()
            }

        }
    }

    public override fun onStart() {
        super.onStart()

        val curretUser = auth.currentUser

        if(curretUser == null){
            Toast.makeText(this, "No hay usuarios autentucados", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Ya estas autenticado", Toast.LENGTH_LONG).show()

        }
    }



    public override fun onDestroy() {
        super.onDestroy()

        auth.signOut()
    }
}