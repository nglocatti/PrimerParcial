package com.example.a1parcial.Fragments.ArticulosActivity

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1parcial.Adapters.ArticulosListAdapter
import com.example.a1parcial.DataBase.Articulos.ArtActualDao
import com.example.a1parcial.DataBase.Articulos.ArticulosDao
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.Entities.Articulos.Articulos

import com.example.a1parcial.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class ListaArticulosFragment : Fragment() {
    lateinit var ListaArticulosView : View

    private var dbarticulos : DataBase? = null
    private var articulosDao : ArticulosDao? = null
    private var artactDao : ArtActualDao? = null

    lateinit var rec_Art : RecyclerView
    lateinit var btn_Add : FloatingActionButton

    var listArticulos : MutableList<Articulos>? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var articulosListAdapter: ArticulosListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ListaArticulosView = inflater.inflate(R.layout.fragment_lista_articulos, container, false)

        rec_Art = ListaArticulosView.findViewById(R.id.rec_Articulos)
        btn_Add = ListaArticulosView.findViewById(R.id.btn_add)

        setHasOptionsMenu(false)

        return ListaArticulosView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }



    override fun onStart() {
        super.onStart()
        dbarticulos = DataBase.getDataBase(ListaArticulosView.context)
        articulosDao = dbarticulos?.articulosDao()
        artactDao = dbarticulos?.ArtActualDao()

        btn_Add.setOnClickListener()
        {
            val action = ListaArticulosFragmentDirections.actionListFragmentToAddArt(listArticulos?.toTypedArray())
            ListaArticulosView.findNavController().navigate(action)
        }

        listArticulos = articulosDao?.getArticulosList()
        //listArticulos =ListaArticulosFragmentArgs.fromBundle(requireArguments()).newArt?.toMutableList()
        if(listArticulos == null){
            listArticulos = ArrayList<Articulos>()
        }

        rec_Art.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)
        rec_Art.layoutManager = linearLayoutManager

        articulosListAdapter = ArticulosListAdapter(listArticulos!!){onItemClick()}
        rec_Art.adapter = articulosListAdapter
    }

    fun onItemClick (){
        val action = ListaArticulosFragmentDirections.actionListaArticulosFragmentToDetalleContainer()
        ListaArticulosView.findNavController().navigate(action)
    }


}
