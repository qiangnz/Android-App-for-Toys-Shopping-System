package com.example.sd6501_final_project_2173138.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sd6501_final_project_2173138.ui.MainActivity;
import com.example.sd6501_final_project_2173138.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    
    //Existence time, initial value is 0, used to calculate subtraction with the current time (milliseconds)
    private long mExitTime;
    
    private Button btnSignUp;
    private Button btnLogin;
    private TextView info;
    private int counter =5;
    private EditText editUser;
    private EditText editPwd;
    
    private SharedPreferences pref;
    
    private CheckBox rememberPass;
    private String spUsername;
    private String spPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        
        editUser = findViewById(R.id.etUsername);
        editPwd = findViewById(R.id.etPassword);

        info=findViewById(R.id.tvInfo);
        info.setText("Number of attempts remaining: 5");
        
        spUsername = pref.getString("username", "");
        spPassword = pref.getString("password", "");
        editUser.setText(spUsername);
        editPwd.setText(spPassword);


       //login event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUser.getText().toString();
                String password = editPwd.getText().toString();
                if (!LoginCheck.isEmailValid(username) || LoginCheck.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter a valid email or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (username.equals(spUsername) && password.equals(spPassword)) {
                    navMainActivity();
                    finish();
                } else {
                    counter--;
                    info.setText("NumberBu of attempts reaming: "+ String.valueOf(counter));
                    Toast.makeText(LoginActivity.this, "Account or password is incorrect", Toast.LENGTH_SHORT).show();
                    if(counter==0){
                        btnLogin.setEnabled(false);
                    }
                }
            }
        }); // end of login event



        //sign up event
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUser.setText("");
                editPwd.setText("");
                Intent signUpIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(signUpIntent);
            }
        });
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        spUsername = pref.getString("username", "");
        spPassword = pref.getString("password", "");
    }
    
    private void navMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
    
    //check password
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                //System.currentTimeMillis()system current time
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    
}
