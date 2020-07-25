
package com.example.cl3_t5bb_rodriguez_jonathan.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SERVICIORESPONSE implements Serializable, Parcelable
{

    @SerializedName("ObjSERVICIO")
    @Expose
    private ObjSERVICIO ObjSERVICIO;
    @SerializedName("ObjListaSERVICIO")
    @Expose
    private List<ObjListaSERVICIO> ObjListaSERVICIO = new ArrayList<ObjListaSERVICIO>();
    public final static Creator<SERVICIORESPONSE> CREATOR = new Creator<SERVICIORESPONSE>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SERVICIORESPONSE createFromParcel(Parcel in) {
            return new SERVICIORESPONSE(in);
        }

        public SERVICIORESPONSE[] newArray(int size) {
            return (new SERVICIORESPONSE[size]);
        }

    }
            ;

    protected SERVICIORESPONSE(Parcel in) {
        this.ObjSERVICIO = ((ObjSERVICIO) in.readValue((ObjSERVICIO.class.getClassLoader())));
        in.readList(this. ObjListaSERVICIO, (com.example.cl3_t5bb_rodriguez_jonathan.pojo.ObjListaSERVICIO.class.getClassLoader()));
    }

    public SERVICIORESPONSE() {
    }

    public ObjSERVICIO getObjSERVICIO() {
        return ObjSERVICIO;
    }

    public void setObjSERVICIO(ObjSERVICIO ObjSERVICIO) {
        this.ObjSERVICIO = ObjSERVICIO;
    }

    public List<ObjListaSERVICIO> getObjListaSERVICIO() {
        return ObjListaSERVICIO;
    }

    public void setObjListaSERVICIO(List<ObjListaSERVICIO> ObjListaSERVICIO) {
        this.ObjListaSERVICIO = ObjListaSERVICIO;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ObjSERVICIO);
        dest.writeList(ObjListaSERVICIO);
    }

    public int describeContents() {
        return  0;
    }
}
