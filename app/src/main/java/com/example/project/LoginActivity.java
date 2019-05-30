package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText eEmail, ePassword;
    private Button bLogin;
    private String email;
    private String password;
    private Cursor comprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        eEmail = (EditText)findViewById(R.id.edtEmail);
        ePassword = (EditText)findViewById(R.id.edtPass);
        bLogin = (Button)findViewById(R.id.btnLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        if (!validate()) return;

        email = eEmail.getText().toString();
        password = ePassword.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Vui lòng chờ...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (email.equals("admin@gmail.com") && password.equals("admin")){
                            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        }else{
                            progressDialog.dismiss();
                            String mesg = String.format("Login fail!!", false);
                            Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                        }
                    }
                }, 2000);
        eEmail.getText().clear();
        ePassword.getText().clear();

    }

    private boolean validate() {
        boolean valid = true;

        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eEmail.setError("Email không hợp lệ!");
            valid = false;
        } else {
            eEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            ePassword.setError("Mật khẩu 4 - 10 ký tự gồm chữ và số");
            valid = false;
        } else {
            ePassword.setError(null);
        }
        return valid;
    }

}