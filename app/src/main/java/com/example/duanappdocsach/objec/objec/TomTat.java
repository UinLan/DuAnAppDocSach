package com.example.duanappdocsach.objec.objec;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TomTat implements Serializable {
    private String tomTat,id;

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public TomTat(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TomTat(String tomTat) {
        this.tomTat = tomTat;
    }
    public TomTat(JSONObject o) throws JSONException
    {
        tomTat = o.getString("tomtat");
        id = o.getString("id");
    }
}
