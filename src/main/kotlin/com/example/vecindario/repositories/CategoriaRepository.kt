package com.example.vecindario.repositories

import com.example.vecindario.models.Categoria
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class CategoriaRepository(private val jdbcTemplate: JdbcTemplate) {

    fun getCategorias(): List<Categoria> {
        val sql = "SELECT * FROM categorias"
        return jdbcTemplate.query(sql, RowMapper<Categoria> { rs, _ ->
            Categoria(
                rs.getInt("id"),
                rs.getString("nombre")
            )
        })
    }

    fun insertarCategoria(categoria: Categoria): Int {
        val sql = """
            INSERT INTO categorias (nombre) 
            VALUES (?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            categoria.nombre
        )
    }

    fun actualizarCategoria(id: Int, categoria: Categoria): Int {
        val sql = """
            UPDATE categorias 
            SET nombre = ? 
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            categoria.nombre,
            id
        )
    }

    fun borrarCategoria(id: Int): Int {
        val sql = "DELETE FROM categorias WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}