package com.example.a1parcial.Fragments.ArticulosActivity

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a1parcial.Entities.Articulos.Articulos

class DetalleContainerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val articulo_actual = MutableLiveData<Articulos>()

    fun getArticulo () : Articulos?
    { return articulo_actual.value }

    fun setArticulo (art_act : Articulos) {
        articulo_actual.value = art_act
    }

}
