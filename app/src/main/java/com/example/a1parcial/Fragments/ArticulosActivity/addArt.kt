package com.example.a1parcial.Fragments.ArticulosActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.a1parcial.DataBase.Articulos.ArticulosDao
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.Entities.Articulos.Articulos

import com.example.a1parcial.R
import com.example.a1parcial.Tools.check_empty
import com.example.a1parcial.Tools.send_message

/**
 * A simple [Fragment] subclass.
 */
class addArt : Fragment() {
    lateinit var addArtView : View

    var listArticulos : MutableList<Articulos> = ArrayList()

    private var dbarticulos : DataBase? = null
    private var articulosDao : ArticulosDao? = null

    lateinit var edt_Codigo : EditText
    lateinit var edt_Descripcion : EditText
    lateinit var edt_Stock : EditText
    lateinit var spin_Categoria: Spinner

    lateinit var btn_Agregar : Button
    lateinit var btn_Regresar : Button

    val Categorias = arrayOf("Sin Categoría", "Comestibles","Bebidas","Electronica","Deportes")
    var Categoria : String = "Sin categoría"

    companion object{
        fun newInstance() = addArt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addArtView = inflater.inflate(R.layout.fragment_add_art, container, false)

        edt_Codigo = addArtView.findViewById(R.id.edt_Codigo)
        edt_Descripcion = addArtView.findViewById(R.id.edt_Descripcion)
        edt_Stock = addArtView.findViewById(R.id.edt_Stock)
        spin_Categoria = addArtView.findViewById(R.id.spin_categoria)
        btn_Agregar = addArtView.findViewById(R.id.btn_det_Modificar)
        btn_Regresar = addArtView.findViewById(R.id.btn_Regresar)


        val opciones_categoria = this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, Categorias) }
        spin_Categoria.adapter = opciones_categoria
        return addArtView
    }

    override fun onStart() {
        super.onStart()

        dbarticulos = DataBase.getDataBase(addArtView.context)
        articulosDao = dbarticulos?.articulosDao()


        spin_Categoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Categoria = Categorias[position]
            }

        }

        btn_Regresar.setOnClickListener()
        {
            val directions = addArtDirections.actionAddArtToListaArticulosFragment(listArticulos.toTypedArray())
            addArtView.findNavController().navigate(directions)
        }

        btn_Agregar.setOnClickListener()
        {
            var flag = check_empty(this.addArtView, "el código", edt_Codigo)
            if (!flag)
                flag = check_empty(this.addArtView, "la descripción", edt_Descripcion)
            if (!flag)
                flag = check_empty(this.addArtView, "el stock", edt_Stock)
            if (!flag)
            {
                val newArticulo = Articulos(
                    edt_Codigo.text.toString(),
                    edt_Descripcion.text.toString(),
                    edt_Stock.text.toString().toInt(),
                    Categoria)
                articulosDao?.insertArticulo(newArticulo)

                listArticulos = addArtArgs.fromBundle(requireArguments()).listArticulos!!.toMutableList()
                listArticulos.add(newArticulo)

                send_message(this.addArtView, "Articulo creado satisfactoriamente")
                val directions = addArtDirections.actionAddArtToListaArticulosFragment(listArticulos.toTypedArray())
                addArtView.findNavController().navigate(directions)
            }
        }



    }

}
