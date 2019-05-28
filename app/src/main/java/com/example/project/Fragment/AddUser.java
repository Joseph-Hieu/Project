package com.example.project.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project.R;


public class AddUser extends Fragment {

    public Spinner phongBan, bangCap, gioiTinh;
    public EditText tenNV, email, maNV, noiO, soDT;
    public Button register;

    public AddUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_user, container, false);

//        maNV = (EditText) view.findViewById(R.id.edtMaNV);
//        tenNV = view.findViewById(R.id.edtName);
//        email = view.findViewById(R.id.edtEmail);
//        noiO = view.findViewById(R.id.edtNoiO);
//        soDT = view.findViewById(R.id.edtSoDT);
//        gioiTinh = view.findViewById(R.id.spnGioiTinh);
//        phongBan = view.findViewById(R.id.spnPhongBan);
//        bangCap = view.findViewById(R.id.spnBangCap);
//        register = view.findViewById(R.id.btnAddUser);

        return view;
    }


}
