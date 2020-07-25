package com.example.cl3_t5bb_rodriguez_jonathan.rest

import com.example.cl3_t5bb_rodriguez_jonathan.pojo.ObjSERVICIO
import com.example.cl3_t5bb_rodriguez_jonathan.pojo.SERVICIORESPONSE
import com.example.cl3_t5bb_rodriguez_jonathan.tools.Constantes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IServicio {
    @GET(Constantes.GETLISTAR_SERVICIO)
    @Headers("Content-Type: application/json")
    fun getListar(
        @Query(value = "CodigoServicio") pCodigoServicio:Int
    , @Query(value = "NombreCliente") pNombreCliente:String
    , @Query(value = "NumeroOrdenServicio") pNumeroOrdenServicio:String
    , @Query(value = "FechaProgramada") pFechaProgramada:String
    , @Query(value = "Linea") pLinea:String
    , @Query(value = "Estado") pEstado:String
    , @Query(value = "Observaciones") pObservaciones:String
    ): Call<SERVICIORESPONSE>

    @GET(Constantes.GETLISTARKEY_SERVICIO)
    @Headers("Content-Type: application/json")
    fun getListarKey(
        @Query(value = "pCodigoServicio") pCodigoServicio: Int
    ): Call<SERVICIORESPONSE>

    @GET(Constantes.GETRegistraModifica_SERVICIO)
    @Headers("Content-Type: application/json")
    fun getRegistraModifica(
        @Query(value = "pTipoTransaccion") pTipoTransaccion:String ,
        @Query(value = "CodigoServicio") pCodigoServicio:Int
        , @Query(value = "NombreCliente") pNombreCliente:String
        , @Query(value = "NumeroOrdenServicio") pNumeroOrdenServicio:String
        , @Query(value = "FechaProgramada") pFechaProgramada:String
        , @Query(value = "Linea") pLinea:String
        , @Query(value = "Estado") pEstado:String
        , @Query(value = "Observaciones") pObservaciones:String
    ): Call<ObjSERVICIO>

    @GET(Constantes.GETElimina_SERVICIO)
    @Headers("Content-Type: application/json")
    fun getElimina(
        @Query(value = "pCodigoServicio") pCodigoServicio: Int
    ): Call<ObjSERVICIO>













}