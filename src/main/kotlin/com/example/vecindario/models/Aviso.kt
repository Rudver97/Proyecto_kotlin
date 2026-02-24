package com.example.vecindario.models


data class Aviso(
    val id: Int? = null,
    val titulo: String,
    val descripcion: String,
    val usuario_id: String,
    val categoria_id: String,
    val estado: String
)
