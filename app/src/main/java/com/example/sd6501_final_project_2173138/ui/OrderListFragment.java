package com.example.sd6501_final_project_2173138.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sd6501_final_project_2173138.R;
import com.example.sd6501_final_project_2173138.constant.IntentString;
import com.example.sd6501_final_project_2173138.db.DbHandler;
import com.example.sd6501_final_project_2173138.model.OrderModel;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OrderListFragment extends Fragment {
    
    private ListView listview;
    private ArrayList<OrderModel> orderlist;
    private OrderAdapter adapter;
    private DbHandler db;
    private boolean isFirst = false;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderlist, container, false);
        findView(view);
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getOrderListFromDb();
        initListView();
        isFirst = true;
    }
    
    private void getOrderListFromDb() {
        
        db = new DbHandler(getActivity());
        orderlist = new ArrayList<>();
        orderlist.addAll(db.getOrders());
        Collections.reverse(orderlist);  // reverse list
    }
    
    private void findView(View view) {
        //add items button
        listview = view.findViewById(R.id.listview);
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderModel model = orderlist.get(position);
                
                Intent intent = new Intent(getActivity(), PreviewOrderActivity.class);
                intent.putExtra(IntentString.ORDER_EDIT,true);
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentString.ORDER,model);
                intent.putExtras(bundle);
                
                startActivity(intent);
            }
        });
    }
    
    private void initListView() {
        adapter = new OrderAdapter();
        
        listview.setAdapter(adapter);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst) {
            orderlist.clear();
            orderlist.addAll(db.getOrders());
            adapter.notifyDataSetChanged();
        } else {
            isFirst = false;
        }
        
    }
    
    class OrderAdapter extends BaseAdapter {
        
        @Override
        public int getCount() {
            return orderlist.size();
        }
        
        @Override
        public Object getItem(int position) {
            return orderlist.get(position);
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_order_item, null);
            TextView orderID = view.findViewById(R.id.orderID);
            TextView orderInfo = view.findViewById(R.id.orderInfo);
            
            final OrderModel model = orderlist.get(position);
            orderID.setText("#"+model.getOrderId());
            orderInfo.setText(model.getToyInfo());
            return view;
        }
    }
}
