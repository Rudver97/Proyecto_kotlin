package com.example.vecindario.controllers

import com.example.vecindario.models.Aviso
import com.example.vecindario.repositories.AvisoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/avisos")
class AvisoController() {

    @Autowired
    lateinit var avisoRepository: AvisoRepository

    @GetMapping
    fun obtenerAvisos(): List<Aviso> {
        return avisoRepository.getAvisos()
    }

    @PostMapping
    fun crearAviso(@RequestBody nuevoAviso: Aviso): String {
        val result = avisoRepository.insertarAviso(nuevoAviso)
        return if (result > 0) "Aviso creado exitosamente" else "Error al agregar"
    }

    @PutMapping("/{id}")
    fun actualizarAviso(@PathVariable id: Int, @RequestBody aviso: Aviso): String {
        val result = avisoRepository.actualizarAviso(id, aviso)
        return if (result > 0) "Aviso actualizado con éxito" else "Error al actualizar"
    }

    @DeleteMapping("/{id}")
    fun borrarAviso(@PathVariable id: Int): String {
        val result = avisoRepository.borrarAviso(id)
        return if (result > 0) "Aviso con id $id eliminado" else "No se encontró ningún aviso con el id $id"
    }
}

