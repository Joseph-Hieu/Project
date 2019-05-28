package com.example.project;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Placeholder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.Fragment.AddUser;
import com.example.project.object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageAvatar;
    byte[] bytes;

    EditText  tenNV, email, maNV, noiO, soDT;
    Spinner phongBan, bangCap, gioiTinh;
    FirebaseDatabase database;
    DatabaseReference myRef;
    User user;
    private FragmentManager fragmentManager;
    AddUser addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

//        user.setMaNV(addUser.maNV.getText().toString());
//        tenNV = addUser.tenNV.getText().toString();
//        email = addUser.email.getText().toString();
//        noiO = addUser.noiO.getText().toString();
//        soDT = addUser.soDT.getText().toString();
//        gioiTinh = addUser.gioiTinh.getSelectedItem().toString();
//        phongBan = addUser.phongBan.getSelectedItem().toString();
//        bangCap = addUser.bangCap.getSelectedItem().toString();


        maNV = (EditText) findViewById(R.id.edtMaNV);
        tenNV = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        noiO = findViewById(R.id.edtNoiO);
        soDT = findViewById(R.id.edtSoDT);
        gioiTinh = findViewById(R.id.spnGioiTinh);
        phongBan = findViewById(R.id.spnPhongBan);
        bangCap = findViewById(R.id.spnBangCap);


        user = new User();
        myRef = FirebaseDatabase.getInstance().getReference("User");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void getValues(){
//        Toast.makeText(this, maNV.getText().toString(), Toast.LENGTH_SHORT).show();
//        user.setTenNV(tenNV.getText().toString());
//        user.setEmail(email.getText().toString());
//        user.setsNoiO(noiO.getText().toString());
//        user.setSoDT(soDT.getText().toString());
//        user.setGioiTinh(gioiTinh.getSelectedItem().toString());
//        user.setBangCap(bangCap.getSelectedItem().toString());
//        user.setsPhongBan(phongBan.getSelectedItem().toString());

//        user.setMaNV("01");
//        user.setTenNV("NDH");
//        user.setsPhongBan("CNTT");
//        user.setGioiTinh("Nam");
//        user.setEmail("hieunguyenductt6@gmail.com");
//        user.setBangCap("Trungcap");
//        user.setSoDT("0785589181");
//        user.setsNoiO("LD");
    }

    public void btnInsert(View view){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //myRef.setValue(null);
                getValues();
                //myRef.child(user.getMaNV()).setValue(user);
                //Toast.makeText(MainActivity.this, user.getMaNV(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add) {
            fragmentManager = getSupportFragmentManager();
            AddUser addUser = new AddUser();
            fragmentManager.beginTransaction().replace(R.id.content, addUser, addUser.getTag()).commit();
        } else if (id == R.id.pDEV) {
            Toast.makeText(this, "pDEV", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pQLNS) {
            Toast.makeText(this, "pTT", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pTT) {
            Toast.makeText(this, "pQLNS", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pTCKT) {
            Toast.makeText(this, "pTCKT", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.infor) {
            Toast.makeText(this, "My Infor", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
