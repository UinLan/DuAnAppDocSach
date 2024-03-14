package com.example.duanappdocsach.objec;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.adapter.BillAdapter;
import com.example.duanappdocsach.objec.objec.Bill;
import com.example.duanappdocsach.objec.objec.BillRepository;

import java.util.List;

public class PaidBillsActivity extends AppCompatActivity {
    private ListView listViewPaidBills;
    private List<Bill> paidBills;
    private ArrayAdapter<Bill> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_bills);

        listViewPaidBills = findViewById(R.id.listViewPaidBills);

        // Lấy thông tin người mua và gói mua từ Intent
        Intent intent = getIntent();
        String buyerName = intent.getStringExtra("buyerName");
        String buyerPhoneNumber = intent.getStringExtra("buyerPhoneNumber");
        String buyerEmail = intent.getStringExtra("buyerEmail");
        String packageName = intent.getStringExtra("packageName");
        int price = intent.getIntExtra("price", 0);
        String status = intent.getStringExtra("status");
        // Lấy danh sách hóa đơn đã thanh toán từ BillRepository
        paidBills = BillRepository.getInstance().getPaidBills();
        if (paidBills == null || paidBills.isEmpty()) {
            Toast.makeText(this, "Chưa có hóa đơn", Toast.LENGTH_SHORT).show();
        }
        else if (buyerName != null && buyerPhoneNumber != null && buyerEmail != null && packageName != null && price != 0) {
            // Tạo hóa đơn mới và thêm vào danh sách
            Bill bill = new Bill(buyerName, buyerPhoneNumber, buyerEmail, packageName, price,status);
            BillRepository.getInstance().addBill(bill);
        }
        // Tạo adapter tùy chỉnh và gán cho ListView
        adapter = new BillAdapter(this, paidBills);
        listViewPaidBills.setAdapter(adapter);
    }
}
