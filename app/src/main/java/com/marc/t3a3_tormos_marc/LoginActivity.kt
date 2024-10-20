package com.marc.t3a3_tormos_marc

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marc.t3a3_tormos_marc.databinding.ActivityLoginBinding
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var entradaValida = true
        binding.btEntrar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            if (binding.tietDni.text.toString().length == 9) {
                if (binding.tietDni.text.toString().matches(Regex("\\d{8}[A-Z]?"))) {
                    binding.dni.error = null
                    entradaValida=true
                } else {
                    binding.dni.error = "El DNI tiene que contener 8 digitos y una letra mayuscula"
                    entradaValida = false
                }
            } else {
                binding.dni.error = "Introduce un DNI correcto, 8 digitos y una letra mayuscula"
                entradaValida = false
            }
            if (binding.tietPassw.text.toString().length >= 8) {
                if (binding.tietPassw.text.toString()
                        .matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!_-]).{8,}$"))

                ){

                    binding.passw.error = null
                    entradaValida=true
                }
                else {
                    binding.passw.error =
                        "La contraseña debe tener al menos 8 caracteres, con 1 minúscula, 1 mayúscula, 1 número y 1 símbolo"
                    binding.passw.errorIconDrawable = null
                    entradaValida = false
                }
            } else {
                binding.passw.error = "Introduce una contraseña con almenos 8 de longitud"
                binding.passw.errorIconDrawable = null
                entradaValida = false
            }
            if (entradaValida) {
                intent.putExtra("Usuario", binding.tietDni.text.toString())
                startActivity(intent)
            }
        }

        binding.btSalir.setOnClickListener {
            exitProcess(0);
        }
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}