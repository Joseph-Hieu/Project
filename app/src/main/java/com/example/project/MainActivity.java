package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Fragment.AddUserFragment;
import com.example.project.Fragment.AllUserFragment;
import com.example.project.Fragment.HomeFragment;
import com.example.project.adapter.MyAdapter;
import com.example.project.object.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageAvatar;
    byte[] bytes;

//    EditText  tenNV, email, maNV, noiO, soDT;
//    Spinner phongBan, bangCap, gioiTinh;
    FirebaseDatabase database;
    public DatabaseReference myRef;
    User user;
    private FragmentManager fragmentManager;
    Fragment fragment = null;
    AddUserFragment addUser;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        user = new User();
        myRef = FirebaseDatabase.getInstance().getReference("User");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myDataset.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    try{
                        User user = messageSnapshot.getValue(User.class);
                        myDataset.add(user);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //todo
            }
        });

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

        showHome();

        Query query = myRef.orderByChild("sPhongBan").equalTo("CNTT");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("issue","issue"+ issue)  ;  // do with your result
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    private void showHome(){
        fragment = new HomeFragment();
        if(fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        fragmentManager.beginTransaction().replace(R.id.content, fragment, fragment.getTag()).commit();
    }

    private void showAllUsers(){
        fragment = new AllUserFragment();
        if(fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        fragmentManager.beginTransaction().replace(R.id.content, fragment, fragment.getTag()).commit();
    }
    private void showAddUser(){
        fragment = new AddUserFragment();
        if(fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        fragmentManager.beginTransaction().replace(R.id.content, fragment, fragment.getTag()).commit();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            if (fragment instanceof HomeFragment){
                super.onBackPressed();
            }else {
                showHome();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add) {
            showAddUser();
        } else if (id == R.id.pDEV) {
            showAllUsers();
            Toast.makeText(this, "pDEV", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pQLNS) {
            Toast.makeText(this, "pTT", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pTT) {
            Toast.makeText(this, "pQLNS", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pTCKT) {
            Toast.makeText(this, "pTCKT", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.infor) {
        }
        else if (id == R.id.logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }

        if (fragment != null){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment, fragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
