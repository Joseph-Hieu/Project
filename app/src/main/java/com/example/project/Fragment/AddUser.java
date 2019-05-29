package com.example.project.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.object.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class AddUser extends Fragment {

    public Spinner phongBan, bangCap, gioiTinh;
    public EditText tenNV, email, maNV, noiO, soDT;
    public MaterialButton register;
    private User user;

    public AddUser() {
        // Required empty public constructor
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_user, container, false);

        maNV = (EditText) view.findViewById(R.id.edtMaNV);
        tenNV = view.findViewById(R.id.edtName);
        email = view.findViewById(R.id.edtEmail);
        noiO = view.findViewById(R.id.edtNoiO);
        soDT = view.findViewById(R.id.edtSoDT);
        gioiTinh = view.findViewById(R.id.spnGioiTinh);
        phongBan = view.findViewById(R.id.spnPhongBan);
        bangCap = view.findViewById(R.id.spnBangCap);
        register = view.findViewById(R.id.btnAddUser);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInsert();
            }
        });

        return view;
    }
    private void getValues(){
        user = new User();
        user.setMaNV(maNV.getText().toString());
        user.setTenNV(tenNV.getText().toString());
        user.setEmail(email.getText().toString());
        user.setsNoiO(noiO.getText().toString());
        user.setSoDT(soDT.getText().toString());
        user.setGioiTinh(gioiTinh.getSelectedItem().toString());
        user.setBangCap(bangCap.getSelectedItem().toString());
        user.setsPhongBan(phongBan.getSelectedItem().toString());

//        user.setTenNV("NDH");
//        user.setsPhongBan("CNTT");
//        user.setGioiTinh("Nam");
//        user.setEmail("hieunguyenductt6@gmail.com");
//        user.setBangCap("Trungcap");
//        user.setSoDT("0785589181");
//        user.setsNoiO("LD");
    }


    public void btnInsert(){
        getValues();
        ((MainActivity) getActivity()).myRef.child(user.getMaNV()).setValue(user);
    }


}
