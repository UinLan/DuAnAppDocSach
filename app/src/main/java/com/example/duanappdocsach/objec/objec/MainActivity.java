package com.example.duanappdocsach.objec.objec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridView;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.DocSachAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
GridView gdvDSSach;
DocSachAdapter adapter;
ArrayList<DocSach> docSachArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
    }
    @SuppressLint("SuspiciousIndentation")
    private void  init(){
    docSachArrayList = new ArrayList<>();
        docSachArrayList.add(new DocSach("Công nghệ BlockChain","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cong-nghe-blockchain.jpg"));
        docSachArrayList.add(new DocSach("Machine Learning Cơ Bản","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-machine-learning-co-ban.jpg"));
        docSachArrayList.add(new DocSach("Linux All-In-One For Dummies – 5Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-linux-all-in-one-for-dummies-5th-edition.jpg"));
        docSachArrayList.add(new DocSach("Beginning Programming With Java For Dummies – 4Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-beginning-programming-with-java-for-dummies-4th-edition.jpg"));
        docSachArrayList.add(new DocSach("Công nghệ BlockChain","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cong-nghe-blockchain.jpg"));
        docSachArrayList.add(new DocSach("Machine Learning Cơ Bản","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-machine-learning-co-ban.jpg"));
        docSachArrayList.add(new DocSach("Linux All-In-One For Dummies – 5Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-linux-all-in-one-for-dummies-5th-edition.jpg"));
        docSachArrayList.add(new DocSach("Beginning Programming With Java For Dummies – 4Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-beginning-programming-with-java-for-dummies-4th-edition.jpg"));
        docSachArrayList.add(new DocSach("Công nghệ BlockChain","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cong-nghe-blockchain.jpg"));
        docSachArrayList.add(new DocSach("Machine Learning Cơ Bản","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-machine-learning-co-ban.jpg"));
        docSachArrayList.add(new DocSach("Linux All-In-One For Dummies – 5Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-linux-all-in-one-for-dummies-5th-edition.jpg"));
        docSachArrayList.add(new DocSach("Beginning Programming With Java For Dummies – 4Th Edition","https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-beginning-programming-with-java-for-dummies-4th-edition.jpg"));

        adapter = new DocSachAdapter(this,0,docSachArrayList);
    }
    private void  anhXa(){
        gdvDSSach = findViewById(R.id.gdvDSSach);
    }
    private void  setUp(){
    gdvDSSach.setAdapter(adapter);
    }
    private void  setClick(){}

}