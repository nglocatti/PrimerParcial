@file:Suppress("DEPRECATION")

package com.example.a1parcial.Fragments.ArticulosActivity

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.a1parcial.DataBase.Articulos.ArtActualDao
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.Entities.Articulos.Articulos

import com.example.a1parcial.R

class DetalleCategoria : Fragment() {

    private var dbarticulos : DataBase? = null
    private var artactDao : ArtActualDao? = null

    lateinit var txt_Detalle : TextView
    lateinit var img_cat : ImageView
    lateinit var btn_Regresar : Button

    companion object {
        fun newInstance() = DetalleCategoria()
    }

    private lateinit var viewModel: DetalleCategoriaViewModel
    lateinit var DetalleCategoriaView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DetalleCategoriaView = inflater.inflate(R.layout.detalle_categoria_fragment, container, false)

        txt_Detalle = DetalleCategoriaView.findViewById(R.id.txt_detallecat)
        img_cat = DetalleCategoriaView.findViewById(R.id.img_CatActual)
        btn_Regresar = DetalleCategoriaView.findViewById(R.id.btn_cat_Regresar)

        return DetalleCategoriaView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetalleCategoriaViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        dbarticulos = DataBase.getDataBase(DetalleCategoriaView.context)
        artactDao = dbarticulos?.ArtActualDao()
        val articuloactual : Articulos? = artactDao?.loadArtActual()

        when(articuloactual?.categoria.toString()){
            "Comestibles"-> {
                img_cat.setImageResource(R.mipmap.comestibles)
                txt_Detalle.setText(R.string.det_Comestibles)
            }
            "Bebidas"-> {
                img_cat.setImageResource(R.mipmap.bebidas)
                txt_Detalle.setText(R.string.det_Bebidas)
            }
            "Electronica"-> {
                img_cat.setImageResource(R.mipmap.electronica)
                txt_Detalle.setText(R.string.det_Electronica)
            }
            "Deportes"-> {
                img_cat.setImageResource(R.mipmap.deportes)
                txt_Detalle.setText(R.string.det_Deportes)
            }
            "Sin Categoría"->{
                txt_Detalle.text = "Sin Categoria"
            }
        }

        btn_Regresar.setOnClickListener{
            val action =DetalleContainerDirections.actionDetalleContainerToListaArticulosFragment()
            DetalleCategoriaView.findNavController().navigate(action)
        }
    }
}
