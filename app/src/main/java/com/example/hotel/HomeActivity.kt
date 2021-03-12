package com.example.hotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class  ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setUp(email ?:"", provider ?:"")
    }

    //Aqui recibimos los parametros de email y el provider y los mostramos
    private fun setUp(email:String, provider:String){
        title = "Inicio"
        emailTextView.text=email
        providerTextView.text = provider

        //Al boton de logout le damos la funcion de que nos quite el usuario y nos mande al inicio
        logOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}