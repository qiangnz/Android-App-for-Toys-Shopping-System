package com.example.sd6501_final_project_2173138.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sd6501_final_project_2173138.R;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    
    private Button btnSignIn;
    private Button btnCreateAccount;
    
    private EditText editUser;
    private EditText editPwd;
    private EditText etRpPassword;
    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        editUser = (EditText) findViewById(R.id.etUsername);
        editPwd = (EditText) findViewById(R.id.etPassword);
        etRpPassword = (EditText) findViewById(R.id.etRpPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnSignIn = findViewById(R.id.btnLogin);
        
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bckToLoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(bckToLoginIntent);
            }
        });
        
        mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);
        editor = mySharedPreferences.edit();//SharedPreferences.Editor
        
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if (!LoginCheck.isEmailValid(editUser.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Incorrect email address format!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (LoginCheck.isEmpty(editPwd.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
    
                if (!editPwd.getText().toString().equals(etRpPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Confirm password incorrect!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                editor.putString("username", editUser.getText().toString());
                editor.putString("password", editPwd.getText().toString());
                editor.commit(); //
                Toast.makeText(RegisterActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
