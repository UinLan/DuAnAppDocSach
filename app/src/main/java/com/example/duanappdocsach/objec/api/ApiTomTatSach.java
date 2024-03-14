package com.example.duanappdocsach.objec.api;

import android.os.AsyncTask;

import com.example.duanappdocsach.objec.interfaces.LayChuongVe;
import com.example.duanappdocsach.objec.interfaces.LayTomTatVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiTomTatSach extends AsyncTask<Void,Void,Void> {
    String data;
    LayTomTatVe layTomTatVe;
    String idSach;
    public ApiTomTatSach(LayTomTatVe layTomTatVe, String idSach) {
        this.layTomTatVe = layTomTatVe;
        this.layTomTatVe.batDau();
        this.idSach = idSach;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://uinlan.000webhostapp.com/LayTomTat.php?id="+idSach)
                .build();
        data = null;
        try
        {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
   if(data == null)
   {
       this.layTomTatVe.biLoi();
   }
   else
   {
       this.layTomTatVe.ketThuc(data);
   }
    }
}
