package com.example.project.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.object.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;


public class AddUserFragment extends Fragment {
    private static final int REQUEST = 1001;

    public Spinner phongBan, bangCap, gioiTinh;
    public EditText tenNV, email, maNV, noiO, soDT;
    public MaterialButton register;
    private User user;
    private ImageView imgAvatar;
    private UploadTask uploadTask;

    public AddUserFragment() {
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
        imgAvatar =  (ImageView)view.findViewById(R.id.imgAvatar);
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
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        });
        return view;
    }

    private void showGallery() {

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA};
            if (!hasPermissions( getContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity)  getContext(), PERMISSIONS, REQUEST );
            } else {
                TedBottomPicker.with(getActivity())
                        .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                            @Override
                            public void onImageSelected(Uri uri) {
                                uploadImage(uri.getPath());
                            }
                        });
            }
        } else {
            TedBottomPicker.with(getActivity())
                    .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                        @Override
                        public void onImageSelected(Uri uri) {
                            // here is selected image uri
                            // upload image
                            uploadImage(uri.getPath());
                        }
                    });
        }


    }

    private void uploadImage(String path){
        if(path == null || path.isEmpty()){
            return;
        }
        Toast.makeText(getContext(), "Uploading...",Toast.LENGTH_LONG).show();
        // here is selected image uri
        Uri file = Uri.fromFile(new File(path));
        StorageReference imgReference = ((MainActivity) getActivity()).storageRef.child("images/"+file.getLastPathSegment());
        uploadTask = imgReference.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return imgReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri taskResult = task.getResult();
                    Picasso.get().load(taskResult.toString()).into(imgAvatar);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showGallery();
                } else {
                    Toast.makeText(getContext(), "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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
