package com.example.vecindario.models

data class Usuario(
    val id: Int? = null,
    val nombre: String,
    val email: String,
    val direccion: String,
    val contraseña: String,
    val fecha_registro: String? = null
)
