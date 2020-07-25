package com.example.cl3_t5bb_rodriguez_jonathan.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.cl3_t5bb_rodriguez_jonathan.R
import com.example.cl3_t5bb_rodriguez_jonathan.pojo.ObjListaSERVICIO
import kotlinx.android.synthetic.main.registroservicios.view.*

public class AdaptadorServicio(val ListaInterna:List<ObjListaSERVICIO>, val clickListener: (ObjListaSERVICIO) -> Unit): RecyclerView.Adapter<AdaptadorServicio.AdaptadorServicioViewHolder>(){
var onItemClick: ((ObjListaSERVICIO) -> Unit)? = null
    public class AdaptadorServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(oObjListaSERVICIO: ObjListaSERVICIO)
        {
            val tvCodigoServicio = itemView.findViewById(R.id.CodigoServicio_ITEM) as TextView
            val tvNombreCliente = itemView.findViewById(R.id.NombreCliente_ITEM) as TextView
            val tvNumeroOrdenServicio = itemView.findViewById(R.id.NumeroOrdenServicio_ITEM) as TextView
            val tvFechaprogramada = itemView.findViewById(R.id.FechaProgramada_ITEM) as TextView
            val tvLinea = itemView.findViewById(R.id.Linea_ITEM) as TextView
            val tvEstado = itemView.findViewById(R.id.Estado_ITEM) as TextView
            val tvObservaciones = itemView.findViewById(R.id.Observaciones_ITEM) as TextView
            tvCodigoServicio.text = oObjListaSERVICIO.codigoServicio.toString()
            tvNombreCliente.text = oObjListaSERVICIO.nombreCliente.toString()
            tvNumeroOrdenServicio.text = oObjListaSERVICIO.numeroOrdenServicio.toString()
            tvFechaprogramada.text = oObjListaSERVICIO.fechaProgramada.toString()
            tvLinea.text = oObjListaSERVICIO.linea.toString()
            tvEstado.text = oObjListaSERVICIO.estado.toString()
            tvObservaciones.text = oObjListaSERVICIO.observaciones.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorServicioViewHolder {
        val v: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.registroservicios,parent,false)
        return AdaptadorServicio.AdaptadorServicioViewHolder(v)
    }

    override fun getItemCount(): Int {
        return ListaInterna.size
    }

    override fun onBindViewHolder(holder: AdaptadorServicioViewHolder, position: Int) {
        holder?.itemView.CodigoServicio_ITEM?.text = ListaInterna.get(position).codigoServicio.toString()
        holder?.itemView.NombreCliente_ITEM?.text = ListaInterna.get(position).nombreCliente.toString()
        holder?.itemView.NumeroOrdenServicio_ITEM?.text = ListaInterna.get(position).numeroOrdenServicio.toString()
        holder?.itemView.FechaProgramada_ITEM?.text = ListaInterna.get(position).fechaProgramada.toString()
        holder?.itemView.Linea_ITEM?.text = ListaInterna.get(position).linea.toString()
        holder?.itemView.Estado_ITEM?.text = ListaInterna.get(position).estado.toString()
        holder?.itemView.Observaciones_ITEM?.text = ListaInterna.get(position).observaciones.toString()
        holder?.itemView?.setOnClickListener{clickListener(ListaInterna.get(position))}
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        var tvTitle: TextView = itemView.findViewById(R.id.CodigoServicio_ITEM)
        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(ListaInterna[adapterPosition])
            }
        }

        override fun onClick(v: View?) {
            onItemClick?.invoke(ListaInterna[adapterPosition])
        }
    }
}