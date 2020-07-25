package com.example.cl3_t5bb_rodriguez_jonathan

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.example.cl3_t5bb_rodriguez_jonathan.adaptador.AdaptadorServicio
import com.example.cl3_t5bb_rodriguez_jonathan.pojo.ObjListaSERVICIO
import com.example.cl3_t5bb_rodriguez_jonathan.pojo.ObjSERVICIO
import com.example.cl3_t5bb_rodriguez_jonathan.pojo.SERVICIORESPONSE
import com.example.cl3_t5bb_rodriguez_jonathan.rest.IServicio
import com.example.cl3_t5bb_rodriguez_jonathan.rest.RestServicio
import com.example.cl3_t5bb_rodriguez_jonathan.tools.Constantes
import com.example.cl3_t5bb_rodriguez_jonathan.tools.ObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    lateinit var oLista: List<ObjListaSERVICIO>
    lateinit var oAdaptadorServicio: AdaptadorServicio
    var TIPOACCION:String = "N"

    lateinit var oNuevoSErvicio: Dialog
    lateinit var oContext: Context
    lateinit var oObjSERVICIO: ObjListaSERVICIO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        oContext=this
        btnConsultaServicio.setOnClickListener()
        {
            buscarServicio()
        }

        btnNuevoServicio.setOnClickListener()
        {
            CargarVentanaServicio()
        }
    }

    public fun buscarServicio()
    {
        oLista = ArrayList<ObjListaSERVICIO>();
        val oIServicio: IServicio
        oIServicio = RestServicio().getSERVICIO()!!.create(IServicio::class.java)
        val call: Call<SERVICIORESPONSE> = oIServicio.getListar(0,edtNombreCliente.text.toString(),"",
            "","","","")

        call.enqueue(object : Callback<SERVICIORESPONSE?> {
            override fun onResponse(call: Call<SERVICIORESPONSE?>?,
                                    response: retrofit2.Response<SERVICIORESPONSE?>
            ) {
                Log.d("body",response.body().toString())
                try{
                    oLista = response.body()!!.objListaSERVICIO
                    MostrarListado()
                }catch(e: java.lang.Exception){
                    Log.d("app", e.toString())
                }
            }

            override fun onFailure(
                call: Call<SERVICIORESPONSE?>?,
                t: Throwable
            ) {
                Log.d("ERROR", t.toString())
            }
        })
    }

    public fun CargarVentanaServicio()
    {
        oNuevoSErvicio = Dialog(oContext)
        oNuevoSErvicio.requestWindowFeature(Window.FEATURE_NO_TITLE)
        oNuevoSErvicio.setCancelable(false)
        oNuevoSErvicio.setContentView(R.layout.nuevoservicioactivity)

        val obtnagregarNuevoserv = oNuevoSErvicio.findViewById(R.id.btnagregarNuevoserv) as Button
        val obtnCancelarNuevoserv = oNuevoSErvicio.findViewById(R.id.btnCancelarNuevoserv) as Button
        obtnagregarNuevoserv.setOnClickListener{
            GrabarRegistro()
            oNuevoSErvicio.dismiss() }
        obtnCancelarNuevoserv.setOnClickListener{ oNuevoSErvicio.dismiss()}
        oNuevoSErvicio.show()
    }

    fun GrabarRegistro()
    {
        val oedtnombreclientenuevo = oNuevoSErvicio.findViewById(R.id.edtnombreclientenuevo) as EditText
        val oedtnumeroordennuevo = oNuevoSErvicio.findViewById(R.id.edtnumeroordennuevo) as EditText
        val oedtfechaprogramadanuevo = oNuevoSErvicio.findViewById(R.id.edtfechaprogramadanuevo) as EditText
        val oedtlineanuevo = oNuevoSErvicio.findViewById(R.id.edtlineanuevo) as EditText
        val oedtestadonuevo = oNuevoSErvicio.findViewById(R.id.edtestadonuevo) as EditText
        val oedtobservacionesnuevo = oNuevoSErvicio.findViewById(R.id.edtobservacionesnuevo) as EditText

        if (TIPOACCION == "N")
        {
            oObjSERVICIO = ObjListaSERVICIO()
            oObjSERVICIO.codigoServicio=0;
        }

        oObjSERVICIO.nombreCliente = oedtnombreclientenuevo.text.toString()
        oObjSERVICIO.numeroOrdenServicio = oedtnumeroordennuevo.text.toString()
        oObjSERVICIO.fechaProgramada = oedtfechaprogramadanuevo.text.toString()
        oObjSERVICIO.linea = oedtlineanuevo.text.toString()
        oObjSERVICIO.estado = oedtestadonuevo.text.toString()
        oObjSERVICIO.observaciones = oedtobservacionesnuevo.text.toString()

        val oIServicio: IServicio
        oIServicio = RestServicio().getSERVICIO()!!.create(IServicio::class.java)
        val call: Call<ObjSERVICIO> = oIServicio.getRegistraModifica( TIPOACCION,oObjSERVICIO.codigoServicio,
            oObjSERVICIO.nombreCliente,
            oObjSERVICIO.numeroOrdenServicio,
            oObjSERVICIO.fechaProgramada,
            oObjSERVICIO.linea,
            oObjSERVICIO.estado,
            oObjSERVICIO.observaciones)

        call.enqueue(object : Callback<ObjSERVICIO?> {
            override fun onResponse(call: Call<ObjSERVICIO?>?,
                                    response: retrofit2.Response<ObjSERVICIO?>
            ) {
                Log.d("body",response.body().toString())
                try {
                    oObjSERVICIO.codigoServicio = response.body()!!.codigoServicio
                    ConsultarSErvicios()
                } catch (e: java.lang.Exception){
                    Log.d("app", e.toString())
                }
            }

            override fun onFailure(
                call: Call<ObjSERVICIO?>?,
                t: Throwable
            ) {
                Log.d("ERROR", t.toString())
            }
        })

    }

    fun MostrarRegistro()
    {
        var oedtnombreclientenuevo = oNuevoSErvicio.findViewById(R.id.edtnombreclientenuevo) as EditText
        var oedtnumeroordennuevo = oNuevoSErvicio.findViewById(R.id.edtnumeroordennuevo) as EditText
        var oedtfechaprogramadanuevo = oNuevoSErvicio.findViewById(R.id.edtfechaprogramadanuevo) as EditText
        var oedtlineanuevo = oNuevoSErvicio.findViewById(R.id.edtlineanuevo) as EditText
        var oedtestadonuevo = oNuevoSErvicio.findViewById(R.id.edtestadonuevo) as EditText
        var oedtobservacionesnuevo = oNuevoSErvicio.findViewById(R.id.edtobservacionesnuevo) as EditText

        oedtnombreclientenuevo.setText(oObjSERVICIO.nombreCliente)
        oedtnumeroordennuevo.setText(oObjSERVICIO.numeroOrdenServicio)
        oedtfechaprogramadanuevo.setText(oObjSERVICIO.fechaProgramada)
        oedtlineanuevo.setText(oObjSERVICIO.linea)
        oedtestadonuevo.setText(oObjSERVICIO.estado)
        oedtobservacionesnuevo.setText(oObjSERVICIO.observaciones)

    }

    fun MostrarListado()
    {
        oAdaptadorServicio= AdaptadorServicio(oLista)
        {
            oObjSERVICIO = it
            TIPOACCION = "A"
            CargarVentanaServicio()
            MostrarRegistro()
        }
        lvListaServicio.setAdapter(oAdaptadorServicio)

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        lvListaServicio.setLayoutManager(llm)
        lvListaServicio.setHasFixedSize(true)
        lvListaServicio.setAdapter(oAdaptadorServicio)
    }

    fun ConsultarVolley()
    {
        var oVolley = Volley.newRequestQueue(this)
        var strUrl =Constantes.ENDPOINT + Constantes.GETLISTAR_SERVICIO

        val params = HashMap<String,String>()
        params["NombreCliente"] = edtNombreCliente.text.toString()

        var objRequest=ObjectRequest<SERVICIORESPONSE>(
            Request.Method.GET,
            strUrl,
            params as Map<String, Any>?,
            SERVICIORESPONSE::class.java,
            Response.Listener {
                oLista = it.objListaSERVICIO
                MostrarListado()
            },
            Response.ErrorListener {
                print("Response is: ${it.message}")
            }
        )
        oVolley.add(objRequest)
    }

    fun ConsultarSErvicios()
    {
        var oVolley = Volley.newRequestQueue(this)
        var strUrl =Constantes.ENDPOINT + Constantes.GETLISTAR_SERVICIO

        val params = HashMap<String,String>()
        params["NombreCliente"] = edtNombreCliente.text.toString()

        var objRequest=ObjectRequest<SERVICIORESPONSE>(
            Request.Method.GET,
            strUrl,
            params as Map<String, Any>?,
            SERVICIORESPONSE::class.java,
            Response.Listener {
                oLista = it.objListaSERVICIO
                MostrarListado()
            },
            Response.ErrorListener {
                print("Response is: ${it.message}")
            }
        )
        oVolley.add(objRequest)
    }

}
