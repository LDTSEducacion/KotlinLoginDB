package com.example.hotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //Esto sirve para que la pantalla de carga se quede por dos segundos
        //y asi se vea el logo para dar al usuarios sensacion de carga
        Thread.sleep(2000)
        setTheme(R.style.SplashTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Aqui hacemos llamada a la base de datos
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion de firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //Setup de la aplicacion
        setUp()
    }

    private fun setUp(){

        //El titulo este solo cambia el nombre de la barra superior para que el usuarios sepa
        //donde se encuentra
        title = "Autenticacion"

        //Conecta con el boton de registro y llama a una funcion que nos facilita
        //Firebase para registrar usuarios
        signUpButton.setOnClickListener{

            //Comprueba que el email y la contraseña este vacias
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                    //Pasa los parametros a la base de datos
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailEditText.text.toString(),
                            passwordEditText.text.toString()).addOnCompleteListener{
                                if(it.isSuccessful){
                                    //Si es correcto te manda a la pantalla de home
                                    showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                                }else{
                                    //Si no funciona te muestra un alert
                                    showAlert()
                                }
                    }
                }
        }

        //Conecta con firebase y loguea al usuario con el metodo que nos proporciona firebase
        logInButton.setOnClickListener{
            //Esto hace lo mismo que arriba
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
                }
        }
    }

    //Esto muestra el error en caso de que el registro o el login fallen
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    //Esta accion lo que hce es mandar al usuario a la pantalla de home con los parametros que le demos
    private fun showHome(email:String, provider:ProviderType){

        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}