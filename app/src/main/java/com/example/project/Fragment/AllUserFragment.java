package com.example.project.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.adapter.MyAdapter;
import com.example.project.object.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AllUserFragment extends Fragment {

    public String phongBan = "CNTT";
    public Spinner bangCap;
    public Spinner gioiTinh;
    public EditText tenNV, email, maNV, noiO, soDT;
    public MaterialButton register;
    private User user;
    private View view;
    private RecyclerView recyclerView;
    private List<User> myDataset = new ArrayList<>();
    private MyAdapter mAdapter;

    public AllUserFragment(String phongban) {
        this.phongBan = phongban;
        // Required empty public constructor
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_all_user, container, false);
        setupData();
        return view;
    }

    private void setupData() {

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        Query query = ((MainActivity)getActivity()).myRef.orderByChild("sPhongBan").equalTo(phongBan);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataset.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                        try{
                            User user = messageSnapshot.getValue(User.class);
                            myDataset.add(user);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }else{
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
