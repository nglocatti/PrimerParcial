package com.example.a1parcial.DataBase.Articulos

import androidx.room.*
import com.example.a1parcial.Entities.Articulos.ArtActual
import com.example.a1parcial.Entities.Articulos.Articulos

@Dao
interface ArtActualDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtActual(Art_Actual: ArtActual)

    @Update
    fun updateArtActual(Articulo: ArtActual?)

    @Delete
    fun deleteArtActual(Articulo: ArtActual?)

    @Query("SELECT * FROM ArtActual WHERE codigo = :idArticulo")
    fun loadArtActualById(idArticulo: String): ArtActual?

    @Query("SELECT codArticulo, Descripcion, Stock, Categoria FROM ArtActual WHERE codigo = 'Art_Actual'")
    fun loadArtActual(): Articulos?
}
