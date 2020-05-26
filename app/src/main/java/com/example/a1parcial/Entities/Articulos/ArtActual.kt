package com.example.a1parcial.Entities.Articulos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArtActual")
class ArtActual(art_actual: Articulos) {
    @PrimaryKey
    @ColumnInfo(name = "codigo")
    var codigo: String = "Art_Actual"

    @ColumnInfo(name = "codArticulo")
    var codarticulo: String = art_actual.codarticulo

    @ColumnInfo(name = "Descripcion")
    var descripcion: String? = art_actual.descripcion

    @ColumnInfo(name = "Stock")
    var stock: Int = art_actual.stock

    @ColumnInfo(name = "Categoria")
    var categoria: String? = art_actual.categoria

    constructor() : this(Articulos("Art_Prueba", "Articulo de prueba", 1 ))

}