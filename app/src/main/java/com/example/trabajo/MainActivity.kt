package com.example.trabajo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo.data.model.Tabla
import com.example.trabajo.databinding.ActivityMainBinding
import com.example.trabajo.ui.login.TablasAdapter
import com.example.trabajo.ui.login.afterTextChanged

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
    )
)

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TablasAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val x = filterTables("x")

        adapter = TablasAdapter(listOf())
        binding.rcViewTablas.adapter = adapter
        binding.rcViewTablas.layoutManager = LinearLayoutManager(this)

        binding.filterDataEditText.afterTextChanged {
            adapter.filter.filter(it)
        }

    }

    private fun filterTables(text: String): List<Tabla> {
        return tablaInfo.filter { s ->
            s.bodegaDestino == text
                    || s.bodegaOrigen == text
                    || s.codigoSAP == text
                    || s.numeroDeParte == text
                    || s.descripcionCompleta == text
                    || s.fabricante == text
                    || s.plataforma == text
                    || s.cantidadRequerida == text
                    || s.bodegaDestino == text
                    || s.justificacionDeTrasladoSitio == text
                    || s.ticket == text

        }
    }


}