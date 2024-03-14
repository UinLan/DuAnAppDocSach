package com.example.duanappdocsach.objec.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_bill, parent, false);
        }

        // Lấy đối tượng hóa đơn tại vị trí position
        final Bill currentBill = mBills.get(position);

        // Ánh xạ các TextView trong layout list_item_bill.xml
        TextView textViewBuyerName = listItemView.findViewById(R.id.textViewBuyerName);
        TextView textViewBuyerPhoneNumber = listItemView.findViewById(R.id.textViewBuyerPhoneNumber);
        TextView textViewBuyerEmail = listItemView.findViewById(R.id.textViewBuyerEmail);
        TextView textViewPackageName = listItemView.findViewById(R.id.textViewPackageName);
        TextView textViewPrice = listItemView.findViewById(R.id.textViewPrices);
        TextView textViewStatus = listItemView.findViewById(R.id.textViewStatus);

        // Gán dữ liệu của hóa đơn vào các TextView
        textViewBuyerName.setText("Tên người mua: " + currentBill.getBuyerName());
        textViewBuyerPhoneNumber.setText("Số điện thoại: " + currentBill.getBuyerPhoneNumber());
        textViewBuyerEmail.setText("Email: " + currentBill.getBuyerEmail());
        textViewPackageName.setText("Gói mua: " + currentBill.getPackageName());
        textViewPrice.setText("Giá: " + currentBill.getPrice() +"$");
        textViewStatus.setText("Trạng thái: " + currentBill.getStatus());

        // Bắt sự kiện nhấn vào một hóa đơn trong danh sách
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị dialog để sửa hóa đơn
                showEditDialog(currentBill, position);
            }
        });

        return listItemView;
    }

    private void showEditDialog(final Bill bill, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogView = inflater.inflate(R.layout.dialog_edit_bill, null);
        builder.setView(dialogView);

        // Ánh xạ các thành phần UI trong dialog
        final EditText editTextName = dialogView.findViewById(R.id.editTextBuyerName);
        final EditText editTextPhone = dialogView.findViewById(R.id.editTextBuyerPhoneNumber);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextBuyerEmail);

        // Hiển thị thông tin hóa đơn trong các EditText
        editTextName.setText(bill.getBuyerName());
        editTextPhone.setText(bill.getBuyerPhoneNumber());
        editTextEmail.setText(bill.getBuyerEmail());

        builder.setTitle("Sửa hóa đơn");
        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy thông tin sửa đổi từ các EditText
                String newName = editTextName.getText().toString().trim();
                String newPhone = editTextPhone.getText().toString().trim();
                String newEmail = editTextEmail.getText().toString().trim();

                // Cập nhật thông tin hóa đơn
                bill.setBuyerName(newName);
                bill.setBuyerPhoneNumber(newPhone);
                bill.setBuyerEmail(newEmail);

                // Cập nhật lại danh sách và thông báo
                notifyDataSetChanged();
                Toast.makeText(mContext, "Hóa đơn đã được sửa đổi", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
