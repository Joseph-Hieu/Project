package com.example.project.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;


public class CNTTFragment extends Fragment {


    public CNTTFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CNTTFragment newInstance(String param1, String param2) {
        CNTTFragment fragment = new CNTTFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cntt, container, false);
    }

}
