package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.Bill;

import java.util.List;

public class BillAdapter extends ArrayAdapter<Bill> {
    private Context mContext;
    private List<Bill> mBills;

    public BillAdapter(Context context, List<Bill> bills) {
        super(context, 0, bills);
        mContext = context;
        mBills = bills;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_bill, parent, false);
        }

        // Lấy đối tượng hóa đơn tại vị trí position
        Bill currentBill = mBills.get(position);

        // Ánh xạ các TextView trong layout list_item_bill.xml
        TextView textViewBuyerName = listItemView.findViewById(R.id.textViewBuyerName);
        TextView textViewBuyerPhoneNumber = listItemView.findViewById(R.id.textViewBuyerPhoneNumber);
        TextView textViewBuyerEmail = listItemView.findViewById(R.id.textViewBuyerEmail);
        TextView textViewPackageName = listItemView.findViewById(R.id.textViewPackageName);
        TextView textViewPrice = listItemView.findViewById(R.id.textViewPrices);

        // Gán dữ liệu của hóa đơn vào các TextView
        textViewBuyerName.setText("Buyer Name: " + currentBill.getBuyerName());
        textViewBuyerPhoneNumber.setText("Buyer Phone Number: " + currentBill.getBuyerPhoneNumber());
        textViewBuyerEmail.setText("Buyer Email: " + currentBill.getBuyerEmail());
        textViewPackageName.setText("Package Name: " + currentBill.getPackageName());
        textViewPrice.setText("Price: " + currentBill.getPrice() +"$");


        return listItemView;
    }
}