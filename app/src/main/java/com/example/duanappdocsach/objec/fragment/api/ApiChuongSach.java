package com.example.duanappdocsach.objec.fragment.api;

import android.os.AsyncTask;

import com.example.duanappdocsach.objec.objec.interfaces.LayChuongVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiChuongSach extends AsyncTask<Void,Void,Void> {
    String data;
    LayChuongVe layChuongVe;
    String idSach;
    public ApiChuongSach(LayChuongVe layChuongVe, String idSach) {
        this.layChuongVe = layChuongVe;
        this.layChuongVe.batDau();
        this.idSach = idSach;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://uinlan.000webhostapp.com/layChuong.php?id="+idSach)
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
       this.layChuongVe.biLoi();
   }
   else
   {
       this.layChuongVe.ketThuc(data);
   }
    }
}
