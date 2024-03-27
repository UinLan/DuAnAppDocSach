package com.example.duanappdocsach.objec.fragment.api;

import android.os.AsyncTask;

import com.example.duanappdocsach.objec.objec.interfaces.LayDetailSachVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayDetailSach extends AsyncTask<Void,Void,Void> {
    String data;
    String idChuong;
    LayDetailSachVe layDetailSachVe;
    public ApiLayDetailSach(LayDetailSachVe layDetailSachVe, String idChuong) {
        this.layDetailSachVe = layDetailSachVe;
        this.idChuong = idChuong;
        this.layDetailSachVe.batDau();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(" https://uinlan.000webhostapp.com/layDetail.php?idChuong="+idChuong)



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
       this.layDetailSachVe.biLoi();
   }
   else
   {
       this.layDetailSachVe.ketThuc(data);
   }
    }
}
