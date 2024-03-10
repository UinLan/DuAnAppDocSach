package com.example.duanappdocsach.objec.api;

import android.os.AsyncTask;

import com.example.duanappdocsach.objec.interfaces.LaySachVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLaySach extends AsyncTask<Void,Void,Void> {
    String data;

    public ApiLaySach(LaySachVe laySachVe) {
        this.laySachVe = laySachVe;
        this.laySachVe.batDau();
    }

    LaySachVe laySachVe;
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url(" http://192.168.1.16:3000/books")
                .url(" https://uinlan.000webhostapp.com/laySach.php")
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
       this.laySachVe.biLoi();
   }
   else
   {
       this.laySachVe.ketThuc(data);
   }
    }
}
