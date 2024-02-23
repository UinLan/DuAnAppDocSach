package com.example.duanappdocsach.objec;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duanappdocsach.R;
import com.example.duanappdocsach.objec.fragment.canhan;
import com.example.duanappdocsach.objec.fragment.dangxuat;
import com.example.duanappdocsach.objec.fragment.mucyeuthich;
import com.example.duanappdocsach.objec.fragment.nguoidung;
import com.google.android.material.navigation.NavigationView;

public class Navigation_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private  static final int FRAGMENT_NGUOIDUNG = 0;
    private  static final int FRAGMENT_MUCYEUTHICH = 1;
    private  static final int FRAGMENT_CANHAN = 2;
    private  static final int FRAGMENT_DANGXUAT = 3;
    private int mCurrentFragment = FRAGMENT_NGUOIDUNG;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
//                (this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
//        replaceFragment(new nguoidung());
//        navigationView.getMenu().findItem(R.id.nav_nguoidung).setChecked(true);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_nguoidung) {
            if (mCurrentFragment != FRAGMENT_NGUOIDUNG){
                replaceFragment(new nguoidung());
                mCurrentFragment = FRAGMENT_NGUOIDUNG;
            }
        } else if (id == R.id.nav_dangxuat) {
            if (mCurrentFragment != FRAGMENT_DANGXUAT){
                replaceFragment(new dangxuat());
                mCurrentFragment = FRAGMENT_DANGXUAT;
            }
        } else if (id == R.id.nav_mucuathich) {
            if (mCurrentFragment != FRAGMENT_MUCYEUTHICH){
                replaceFragment(new mucyeuthich());
                mCurrentFragment = FRAGMENT_MUCYEUTHICH;
            }
        } else if (id == R.id.nav_taikhoanvabaomat) {
            if (mCurrentFragment != FRAGMENT_CANHAN){
                replaceFragment(new canhan());
                mCurrentFragment = FRAGMENT_CANHAN;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private  void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}
