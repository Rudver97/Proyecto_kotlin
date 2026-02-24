package com.example.vecindario.repositories

import com.example.vecindario.models.Aviso
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class AvisoRepository(private val jdbcTemplate: JdbcTemplate) {

    fun getAvisos(): List<Aviso> {
        val sql = "SELECT * FROM avisos"
        return jdbcTemplate.query(sql, RowMapper { rs, _ ->
            Aviso(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("descripcion"),
                rs.getString("usuario_id"),
                rs.getString("categoria_id"),
                rs.getString("estado")
            )
        })
    }

    fun insertarAviso(aviso: Aviso): Int {
        val sql = """
            INSERT INTO avisos (id, titulo, descripcion, usuario_id, categoria_id, estado)
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            aviso.id,
            aviso.titulo,
            aviso.descripcion,
            aviso.usuario_id,
            aviso.categoria_id,
            aviso.estado
        )
    }

    fun actualizarAviso(id: Int, aviso: Aviso): Int {
        val sql = """
            UPDATE avisos 
            SET titulo = ?, descripcion = ?, usuario_id = ?, categoria_id = ?, estado = ?
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            aviso.titulo,
            aviso.descripcion,
            aviso.usuario_id,
            aviso.categoria_id,
            aviso.estado,
            id
        )
    }

    fun borrarAviso(id: Int): Int {
        val sql = "DELETE FROM avisos WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}