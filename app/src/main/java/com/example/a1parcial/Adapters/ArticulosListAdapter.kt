package com.example.a1parcial.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.a1parcial.DataBase.Articulos.ArtActualDao
import com.example.a1parcial.DataBase.DataBase
import com.example.a1parcial.Entities.Articulos.ArtActual
import com.example.a1parcial.Entities.Articulos.Articulos
import com.example.a1parcial.R

class ArticulosListAdapter (private var articulosList: MutableList<Articulos>,val adapterOnClick : () -> Unit) :
    RecyclerView.Adapter<ArticulosListAdapter.ArticulosHolder>() {

        companion object {
            private val TAG = "ArticulosListAdapter"
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticulosHolder {
            val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_articulo,parent,false)
            return (ArticulosHolder(
                view
            ))
        }

        override fun getItemCount(): Int {
            return articulosList.size
        }

        override fun onBindViewHolder(holder: ArticulosHolder, position: Int) {
            holder.setCodigo(articulosList[position].codarticulo)
            holder.setDescripcion(articulosList[position].descripcion)
            holder.setImg(articulosList[position].categoria)
            holder.getCardLayout().setOnClickListener {
                holder.artActual(articulosList[position])
                adapterOnClick()
            }
        }

        class ArticulosHolder (v: View) : RecyclerView.ViewHolder(v){

            private var view: View = v

            private var dbarticulos : DataBase? = null
            private var artactDao : ArtActualDao? = null

            fun artActual( Art_Act : Articulos)
            {
                dbarticulos = DataBase.getDataBase(view.context)
                artactDao = dbarticulos?.ArtActualDao()
                val articulo = ArtActual(Art_Act)

                artactDao?.insertArtActual(articulo)
            }

            fun setCodigo(name : String?) {
                val txt : TextView = view.findViewById(R.id.txt_codigo)
                txt.text = name
            }
            fun setDescripcion(name : String?) {
                val txt : TextView = view.findViewById(R.id.txt_descripcion)
                txt.text = name
            }
            fun setImg (categoria : String?){
                val img : ImageView =  view.findViewById(R.id.img_art)
                when(categoria){
                    "Comestibles"->img.setImageResource(R.mipmap.comestibles)
                    "Bebidas"->img.setImageResource(R.mipmap.bebidas)
                    "Electronica"->img.setImageResource(R.mipmap.electronica)
                    "Deportes"->img.setImageResource(R.mipmap.deportes)
                }
            }
            fun getCardLayout (): CardView {
                return view.findViewById(R.id.card_articulo)
            }
            fun getDBList(Articulos : MutableList<Articulos>){
                for (cada_articulo in Articulos)
                {
                    setCodigo(cada_articulo.codarticulo)
                    setDescripcion(cada_articulo.descripcion)
                    setImg(cada_articulo.categoria)
                }
            }

        }
}