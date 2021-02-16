package com.example.sd6501_final_project_2173138.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sd6501_final_project_2173138.Login.RegisterActivity;
import com.example.sd6501_final_project_2173138.R;
import com.example.sd6501_final_project_2173138.constant.IntentString;
import com.example.sd6501_final_project_2173138.db.DbHandler;
import com.example.sd6501_final_project_2173138.model.OrderModel;

public class PreviewOrderActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_order);
        
        TextView textView = findViewById(R.id.order_info);
        TextView order_user_info =findViewById(R.id.order_user_info);
        Button btn = findViewById(R.id.btnConfirmOrder);
        Button btnDeleteOrder = findViewById(R.id.btnDeleteOrder);
        Button btnEditOrderUserInfo = findViewById(R.id.btnEditOrderUserInfo);
        
        final Boolean isEdit = getIntent().getBooleanExtra(IntentString.ORDER_EDIT, false);
        if (isEdit) {
            btn.setText("Update Order");
            btnDeleteOrder.setVisibility(View.VISIBLE);
            btnEditOrderUserInfo.setVisibility(View.VISIBLE);
        } else {
            btn.setText("Confirm Order");
            btnDeleteOrder.setVisibility(View.GONE);
            btnEditOrderUserInfo.setVisibility(View.GONE);
        }
        
        Bundle bundle = getIntent().getExtras();
        
        final OrderModel model = (OrderModel) bundle.getSerializable(IntentString.ORDER);
        
        String orderInfo = model.getToyInfo();
        String userInfo = "Customer Name:" + model.getOrderUsername() + "\n" + "Shipping Address:" + model.getOrderAddress();
        
        textView.setText(orderInfo);
        order_user_info.setText(userInfo);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit) {
                    DbHandler dbHandler = new DbHandler(PreviewOrderActivity.this);
                    dbHandler.updateListDetails(model.getOrderId(), model.getOrderUsername(),
                            model.getOrderAddress(), model.getToyInfo());
                    Toast.makeText(PreviewOrderActivity.this, "Updated Order", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PreviewOrderActivity.this, MainActivity.class));
    
                } else {
                    DbHandler dbHandler = new DbHandler(PreviewOrderActivity.this);
                    long newRodID = dbHandler.insertOrderDetails(model.getOrderUsername(), model.getOrderAddress(),
                            model.getToyInfo());
                    Toast.makeText(PreviewOrderActivity.this, "Added a New Order", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PreviewOrderActivity.this, MainActivity.class));
                }
            }
        });
        
        btnEditOrderUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.setClass(PreviewOrderActivity.this, EditUserInfoActivity.class);
                startActivity(intent);
            }
        });
        
        btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHandler db = new DbHandler(PreviewOrderActivity.this);
                db.deleteList(model.getOrderId());
                Toast.makeText(PreviewOrderActivity.this, "Order Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PreviewOrderActivity.this, MainActivity.class));
            }
        });
    }
}