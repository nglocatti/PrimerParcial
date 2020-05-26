package com.example.a1parcial.DataBase.Articulos

import androidx.room.*
import com.example.a1parcial.Entities.Articulos.Articulos
import com.example.a1parcial.Entities.Login.UserLogin

@Dao
interface ArticulosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticulo(Articulo: Articulos?)

    @Update
    fun updateArticulo(Articulo: Articulos?)

    @Delete
    fun deleteArticulo(Articulo: Articulos?)

    @Query("SELECT * FROM Articulos WHERE codarticulo = :idArticulo")
    fun loadArticuloById(idArticulo: String): Articulos?

    @Query("SELECT * FROM Articulos")
    fun getArticulosList() : MutableList<Articulos>
}