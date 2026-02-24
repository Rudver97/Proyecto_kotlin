package com.example.vecindario.repositories

import com.example.vecindario.models.Usuario
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class UsuarioRepository(private val jdbcTemplate: JdbcTemplate) {

    fun getUsers(): List<Usuario> {
        val sql = "SELECT * FROM usuarios"
        return jdbcTemplate.query(sql, RowMapper { rs, _ ->
            Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("direccion"),
                rs.getString("contraseña"),
                rs.getString("fecha_registro")
            )
        })
    }

    fun insertarUsuario(usuario: Usuario): Int {
        val sql = """
            INSERT INTO usuarios (id, nombre, email, direccion, contraseña, fecha_registro) 
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            usuario.id,
            usuario.nombre,
            usuario.email,
            usuario.direccion,
            usuario.contraseña,
            usuario.fecha_registro
        )
    }

    fun actualizarUsuario(id: Int, usuario: Usuario) : Int {
        val sql = """
            UPDATE USUARIOS SET
                nombre = ?,
                email = ?,
                direccion = ?,
                contraseña = ?
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            usuario.nombre,
            usuario.email,
            usuario.direccion,
            usuario.contraseña,
            id
        )
    }

    fun borrarUsuario(id: Int) : Int{
        val sql = """
            DELETE FROM USUARIOS WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(sql, id)
    }
}