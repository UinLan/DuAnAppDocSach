package com.example.duanappdocsach.objec;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.objec.Bill;
import com.example.duanappdocsach.objec.objec.BillRepository;

public class BanGoiXemSachActivity extends AppCompatActivity {
    Button buttonViewBillDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_goi_xem_sach);
        buttonViewBillDetails = findViewById(R.id.buttonViewBillDetails);

        final RadioGroup radioGroupPackages = findViewById(R.id.radioGroupPackages);
        Button buttonBuyPackage = findViewById(R.id.buttonBuyPackage);

        buttonBuyPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem người dùng đã chọn gói nào
                int selectedPackageId = radioGroupPackages.getCheckedRadioButtonId();
                if (selectedPackageId == -1) {
                    Toast.makeText(BanGoiXemSachActivity.this, "Vui lòng chọn gói xem sách", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Xử lý mua gói xem sách tương ứng
                if (selectedPackageId == R.id.radioButtonBasic) {
                    muaGoiXemSachCoBan();
                } else if (selectedPackageId == R.id.radioButtonPremium) {
                    muaGoiXemSachPremium();
                }
            }
        });
        buttonViewBillDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BanGoiXemSachActivity.this, PaidBillsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void muaGoiXemSachCoBan() {
        // Hiển thị dialog để nhập thông tin mua sách và thanh toán
        showPurchaseDialog("Gói Xem Sách Cơ Bản (100 sách)", 29, "Đã thanh toán");
    }

    private void muaGoiXemSachPremium() {
        // Hiển thị dialog để nhập thông tin mua sách và thanh toán
        showPurchaseDialog("Gói Xem Sách Premium (200 sách)", 59, "Đã thanh toán");
    }

    private void showPurchaseDialog(String packageName, final int price, final String status) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_payment_info, null);
        builder.setView(dialogView);

        // Khởi tạo các đối tượng UI trong dialog
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextPhone = dialogView.findViewById(R.id.editTextPhoneNumber);
        EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        TextView textViewPackage = dialogView.findViewById(R.id.textViewPackage);
        TextView textViewPrice = dialogView.findViewById(R.id.textViewPrice);

        // Hiển thị thông tin gói và giá
        textViewPackage.setText(packageName);
        textViewPrice.setText(String.valueOf(price) + "$");

        builder.setTitle("Thanh toán");
        builder.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy thông tin người mua
                String buyerName = editTextName.getText().toString().trim();
                String buyerEmail = editTextEmail.getText().toString().trim();
                String buyerPhoneNumber = editTextPhone.getText().toString().trim();

                // Kiểm tra xem có thông tin người mua không
                if (buyerName.isEmpty() || buyerPhoneNumber.isEmpty()) {
                    Toast.makeText(BanGoiXemSachActivity.this, "Vui lòng nhập đầy đủ thông tin người mua", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Xử lý thanh toán ở đây
                // Tạo đối tượng Bill từ thông tin người mua
                Bill bill = new Bill(buyerName, buyerPhoneNumber, buyerEmail, packageName, price, status);
                // Trong phương thức onClick của nút "Thanh toán"
                BillRepository billRepository = BillRepository.getInstance();
                billRepository.addBill(bill);
                Toast.makeText(BanGoiXemSachActivity.this, "Đã thanh toán " + packageName, Toast.LENGTH_SHORT).show();
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
