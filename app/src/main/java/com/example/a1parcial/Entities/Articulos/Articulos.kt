package com.example.a1parcial.Entities.Articulos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articulos")
class Articulos(IdArticles: String?, Description: String?, Stock: Int = 0, Categoria: String? = "Sin categoría") : Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "codArticulo")
    var codarticulo: String = IdArticles.toString()

    @ColumnInfo(name = "Descripcion")
    var descripcion: String? = Description

    @ColumnInfo(name = "Stock")
    var stock: Int = Stock

    @ColumnInfo(name = "Categoria")
    var categoria: String? = Categoria

    constructor() : this("Prueba", "Articulo de prueba")

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(codarticulo)
        writeString(descripcion)
        writeInt(stock)
        writeString(categoria)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Articulos> = object : Parcelable.Creator<Articulos> {
            override fun createFromParcel(source: Parcel): Articulos = Articulos(source)
            override fun newArray(size: Int): Array<Articulos?> = arrayOfNulls(size)
        }
    }
}
