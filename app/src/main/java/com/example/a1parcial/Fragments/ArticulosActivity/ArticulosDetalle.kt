@file:Suppress("DEPRECATION")

package com.example.a1parcial.Fragments.ArticulosActivity

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.a1parcial.DataBase.Articulos.ArtActualDao
import com.example.a1parcial.DataBase.Articulos.ArticulosDao
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.Entities.Articulos.Articulos

import com.example.a1parcial.R
import com.example.a1parcial.Tools.send_message

class ArticulosDetalle : Fragment() {

    companion object {
        fun newInstance() = ArticulosDetalle()
    }

    private lateinit var viewModel: ArticulosDetalleViewModel
    private lateinit var viewModel_art: DetalleContainerViewModel

    lateinit var ArticulosDetalleView : View

    private var dbarticulos : DataBase? = null
    private var articulosDao : ArticulosDao? = null
    private var artactDao : ArtActualDao? = null

    lateinit var img_art_actual : ImageView

    lateinit var edt_Codigo : EditText
    lateinit var edt_Descripcion : EditText
    lateinit var edt_Stock : EditText
    lateinit var spin_Categoria: Spinner

    lateinit var btn_Modificar : Button
    lateinit var btn_Regresar : Button

    val Categorias = arrayOf("Sin Categoría", "Comestibles","Bebidas","Electronica","Deportes")
    var Categoria : String = "Sin categoría"

    lateinit var art_act : Articulos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ArticulosDetalleView = inflater.inflate(R.layout.articulos_detalle_fragment, container, false)

        edt_Codigo = ArticulosDetalleView.findViewById(R.id.edt_detCodigo)
        edt_Descripcion = ArticulosDetalleView.findViewById(R.id.edt_detDescripcion)
        edt_Stock = ArticulosDetalleView.findViewById(R.id.edt_detStock)
        spin_Categoria = ArticulosDetalleView.findViewById(R.id.spin_detCategoria)
        btn_Modificar = ArticulosDetalleView.findViewById(R.id.btn_det_Modificar)
        btn_Regresar = ArticulosDetalleView.findViewById(R.id.btn_cat_Regresar)
        img_art_actual = ArticulosDetalleView.findViewById(R.id.img_CatActual)

        val opciones_categoria = this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, Categorias) }
        spin_Categoria.adapter = opciones_categoria

        return ArticulosDetalleView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticulosDetalleViewModel::class.java)
        viewModel_art = ViewModelProviders.of(this).get(DetalleContainerViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lista_articulos_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        dbarticulos = DataBase.getDataBase(ArticulosDetalleView.context)
        articulosDao = dbarticulos?.articulosDao()

        when(item.itemId) {

            R.id.action_editar -> {
                edt_Descripcion.isEnabled = true
                edt_Stock.isEnabled = true
                spin_Categoria.isEnabled = true
                btn_Modificar.visibility = View.VISIBLE
            }
            R.id.action_eliminar -> {
                val builder = AlertDialog.Builder(ArticulosDetalleView.context)
                builder.setMessage("Esta seguro que desea eliminar el artículo?")
                    .setCancelable(false)
                    .setPositiveButton("Si") { _, _ ->
                        val deleteArticulo = Articulos(
                            edt_Codigo.text.toString(),
                            edt_Descripcion.text.toString(),
                            edt_Stock.text.toString().toInt(),
                            Categoria
                        )
                        articulosDao?.deleteArticulo(deleteArticulo)
                        send_message(
                            this.ArticulosDetalleView,
                            "Artículo " + deleteArticulo.codarticulo + " eliminado"
                        )
                        btn_Regresar.callOnClick()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        dbarticulos = DataBase.getDataBase(ArticulosDetalleView.context)
        articulosDao = dbarticulos?.articulosDao()
        artactDao = dbarticulos?.ArtActualDao()

        val articuloactual : Articulos? = artactDao?.loadArtActual()

        edt_Codigo.setText(articuloactual?.codarticulo)
        edt_Codigo.isEnabled = false
        edt_Descripcion.setText(articuloactual?.descripcion)
        edt_Descripcion.isEnabled = false
        edt_Stock.setText(articuloactual?.stock.toString())
        edt_Stock.isEnabled = false
        btn_Modificar.visibility = View.INVISIBLE
        spin_Categoria.isClickable = false
        spin_Categoria.isEnabled = false


        when(articuloactual?.categoria.toString()){
            "Comestibles"-> {
                img_art_actual.setImageResource(R.mipmap.comestibles)
                spin_Categoria.setSelection(Categorias.indexOf("Comestibles"))
            }
            "Bebidas"-> {
                img_art_actual.setImageResource(R.mipmap.bebidas)
                spin_Categoria.setSelection(Categorias.indexOf("Bebidas"))
            }
            "Electronica"-> {
                img_art_actual.setImageResource(R.mipmap.electronica)
                spin_Categoria.setSelection(Categorias.indexOf("Electronica"))
            }
            "Deportes"-> {
                img_art_actual.setImageResource(R.mipmap.deportes)
                spin_Categoria.setSelection(Categorias.indexOf("Deportes"))
            }
        }

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
        btn_Regresar.setOnClickListener{
            setHasOptionsMenu(false)
            val action =DetalleContainerDirections.actionDetalleContainerToListaArticulosFragment()
            ArticulosDetalleView.findNavController().navigate(action)
        }

        btn_Modificar.setOnClickListener{
            val editArticulo = Articulos(
                edt_Codigo.text.toString(),
                edt_Descripcion.text.toString(),
                edt_Stock.text.toString().toInt(),
                Categoria)
            articulosDao?.updateArticulo(editArticulo)
            send_message(this.ArticulosDetalleView, "Artículo ${edt_Codigo.text} modificado")
            btn_Regresar.callOnClick()
        }
    }
}
