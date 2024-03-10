package com.example.duanappdocsach.objec.objec;

import java.util.ArrayList;
import java.util.List;

public class BillRepository {
    private static BillRepository instance;
    private List<Bill> paidBills;

    private BillRepository() {
        paidBills = new ArrayList<>();
    }

    public static synchronized BillRepository getInstance() {
        if (instance == null) {
            instance = new BillRepository();
        }
        return instance;
    }

    public void addBill(Bill bill) {
        paidBills.add(bill);
    }

    public List<Bill> getPaidBills() {
        return paidBills;
    }
}
