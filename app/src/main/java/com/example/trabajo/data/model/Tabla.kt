package com.example.trabajo.data.model

data class Tabla(
    val bodegaOrigen: String,
    val codigoSAP: String,
    val numeroDeParte: String,
    val descripcionCompleta: String,
    val fabricante: String,
    val plataforma: String,
    val cantidadRequerida: String?,
    val bodegaDestino: String,
    val justificacionDeTrasladoSitio: String,
    val ticket: String,
)
