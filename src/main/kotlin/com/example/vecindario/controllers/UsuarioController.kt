package com.example.vecindario.controllers

import com.example.vecindario.repositories.UsuarioRepository
import com.example.vecindario.models.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UsuarioController {

    @Autowired
    lateinit var UsuarioRepository: UsuarioRepository

    @GetMapping ("/usuarios")
    fun obtenerUsuarios(): List<Usuario> {
        return UsuarioRepository.getUsers()
    }

    @PostMapping("/usuarios")
    fun crearUsuario(@RequestBody nuevousuario: Usuario): String {
        val result=UsuarioRepository.insertarUsuario(nuevousuario)
        return if (result>0) "Usuario creado exitosamente"
        else "Error al agregar"
    }

    @PutMapping("/usuarios/{id}")
    fun actualizarUsuario(@PathVariable id: Int, @RequestBody usuario: Usuario): String {
        val result = UsuarioRepository.actualizarUsuario(id, usuario)
        return if (result > 0){ "Usuario actualizado con exito"
        }else{
        "Error al actualizar"
        }
    }

    @DeleteMapping("/usuarios/{id}")
    fun borrarUsuario(@PathVariable id: Int): String {
        val result = UsuarioRepository.borrarUsuario(id)
        return if (result > 0) {
            "Usuario con id $id eliminado"
        } else {
            "No se encontró ningún usuario con el id $id"
        }
    }

}