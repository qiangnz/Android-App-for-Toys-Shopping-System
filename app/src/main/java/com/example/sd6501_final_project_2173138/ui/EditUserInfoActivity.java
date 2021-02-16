package com.example.sd6501_final_project_2173138.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sd6501_final_project_2173138.R;
import com.example.sd6501_final_project_2173138.constant.IntentString;
import com.example.sd6501_final_project_2173138.model.OrderModel;

public class EditUserInfoActivity extends AppCompatActivity {
    
    String toyInfo;
    int orderID;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
    
        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etAddress = findViewById(R.id.etAddress);
        
        final Intent intent = getIntent();
        final Boolean isEdit = intent.getBooleanExtra(IntentString.ORDER_EDIT, false);
        if (isEdit) {
            Bundle bundle = getIntent().getExtras();
            OrderModel model = (OrderModel) bundle.getSerializable(IntentString.ORDER);
            toyInfo = model.getToyInfo();
            orderID = model.getOrderId();
            etUsername.setText(model.getOrderUsername());
            etAddress.setText(model.getOrderAddress());
            
        } else {
            toyInfo = intent.getStringExtra(IntentString.ORDER_TOY_INFO);
        }
        
       
        Button btnPreviewOrder = findViewById(R.id.btnPreviewOrder);
    
        btnPreviewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etUsername.getText().toString())){
                    Toast.makeText(EditUserInfoActivity.this, "Please enter customer name", Toast.LENGTH_SHORT).show();
                    return;
                }
    
                if (TextUtils.isEmpty(etAddress.getText().toString())){
                    Toast.makeText(EditUserInfoActivity.this, "Please enter shipping address", Toast.LENGTH_SHORT).show();
                    return;
                }
    

                
                OrderModel model = new OrderModel();
                model.setOrderUsername(etUsername.getText().toString());
                model.setOrderAddress(etAddress.getText().toString());
                model.setToyInfo(toyInfo);
                if (isEdit){
                    model.setOrderId(orderID);
                }
               
                Intent previewIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentString.ORDER,model);
                previewIntent.putExtra(IntentString.ORDER_EDIT,isEdit);
                previewIntent.putExtras(bundle);
                previewIntent.setClass(EditUserInfoActivity.this,PreviewOrderActivity.class);
                startActivity(previewIntent);
            }
        });
    }
}