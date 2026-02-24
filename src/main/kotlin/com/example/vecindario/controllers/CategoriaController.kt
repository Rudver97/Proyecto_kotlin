package com.example.vecindario.controllers

import com.example.vecindario.models.Categoria
import com.example.vecindario.repositories.CategoriaRepository
import org.springframework.web.bind.annotation.*

@RestController
class CategoriaController(private val CategoriaRepository: CategoriaRepository) {

    @GetMapping("categorias")
    fun obtenerCategorias(): List<Categoria> {
        return CategoriaRepository.getCategorias()
    }

    @PostMapping("categorias")
    fun crearCategoria(@RequestBody categoria: Categoria): String {
        val result = CategoriaRepository.insertarCategoria(categoria)
        return if (result > 0) {
            "Categoría creada exitosamente"
        } else {
            "Error al agregar"
        }
    }

    @PutMapping("/categorias/{id}")
    fun actualizarCategoria(
        @PathVariable id: Int,
        @RequestBody categoria: Categoria
    ): String {
        val result = CategoriaRepository.actualizarCategoria(id, categoria)
        return if (result > 0) {
            "Categoría actualizada con éxito"
        } else {
            "Error al actualizar"
        }
    }

    @DeleteMapping("/categorias/{id}")
    fun borrarCategoria(@PathVariable id: Int): String {
        val result = CategoriaRepository.borrarCategoria(id)
        return if (result > 0) {
            "Categoría con id $id eliminada"
        } else {
            "No se encontró ninguna categoría con el id $id"
        }
    }
}