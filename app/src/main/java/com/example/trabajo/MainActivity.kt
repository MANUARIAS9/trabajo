package com.example.trabajo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo.data.model.Tabla
import com.example.trabajo.databinding.ActivityMainBinding
import com.example.trabajo.ui.login.TablasAdapter
import com.example.trabajo.utils.afterTextChanged

val tablaInfo = listOf(
    Tabla(
        bodegaOrigen = "bodegaOrigen",
        codigoSAP = "codigoSAP",
        numeroDeParte = "numeroDeParte",
        descripcionCompleta = "descripcionCompleta",
        fabricante = "fabricante",
        plataforma = "plataforma",
        cantidadRequerida = "cantidadRequerida",
        bodegaDestino = "bodegaDestino",
        justificacionDeTrasladoSitio = "justificacionDeTrasladoSitio",
        ticket = "ticket",
    ),
    Tabla(
        bodegaOrigen = "bodegaOrigen",
        codigoSAP = "kli",
        numeroDeParte = "numeroDeParte",
        descripcionCompleta = "descripcionCompleta",
        fabricante = "fabricante",
        plataforma = "plataforma",
        cantidadRequerida = "cantidadRequerida",
        bodegaDestino = "bodegaDestino",
        justificacionDeTrasladoSitio = "justificacionDeTrasladoSitio",
        ticket = "ticket",
    )
)

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TablasAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TablasAdapter(tablaInfo)
        binding.rcViewTablas.adapter = adapter
        binding.rcViewTablas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.filterDataEditText.afterTextChanged {
            adapter.filter.filter(it)
        }

        binding.fabSendEmail.setOnClickListener {
            val email = "manuariash9@gmail.com"
            val subject = "Inventario bodegas"
            var body = ""

            tablaInfo.forEach {
                body += "- ${it.codigoSAP}: ${it.descripcionCompleta} \n"
            }

            val uri = Uri.parse("mailto: $email")
                .buildUpon()
                .appendQueryParameter("subject", subject)
                .appendQueryParameter("body", body)
                .build()

            val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
            startActivity(Intent.createChooser(emailIntent, null))
        }
    }

}