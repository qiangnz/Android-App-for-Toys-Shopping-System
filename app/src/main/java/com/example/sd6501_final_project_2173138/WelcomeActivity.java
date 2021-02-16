package com.example.sd6501_final_project_2173138;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sd6501_final_project_2173138.Login.LoginActivity;

//SD6501 Final Project Qiang Zhang 2173138

public class WelcomeActivity extends AppCompatActivity {
    private Button Start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Start=(Button)findViewById(R.id.btnStart);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity();
                finish();
            }
        });
    }
    public void gotoLoginActivity(){
        Intent intent =new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
