package com.example.sd6501_final_project_2173138.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sd6501_final_project_2173138.R;
import com.example.sd6501_final_project_2173138.constant.IntentString;
import com.example.sd6501_final_project_2173138.db.DbHandler;
import com.example.sd6501_final_project_2173138.model.ToyModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ToyListFragment extends Fragment {
    
    private ListView listview;
    private ArrayList<ToyModel> toyList;
    private Button order;
    private ToyAdapter adapter;
    private DbHandler db;
    private int toyNumMark;
    
    public static int[] TOY_IMAGES = {
            R.drawable.lego,
            R.drawable.schleich,
            R.drawable.carrera,
            R.drawable.nerf,
            R.drawable.tomy,
            R.drawable.marvel};
    
    public static String[] TOY_NAMES = {
            "LEGO",
            "Schleich",
            "Carrera",
            "Nerf",
            "Tomy",
            "Marvel"};
    
    public static int[] TOY_PRICES = {
            25,
            15,
            50,
            30,
            55,
            35};
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toylist, container, false);
        findView(view);
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }
    
    private void init() {
        initToyList();
        initListView();
    }
    
    private void initToyList() {
        toyList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ToyModel model = new ToyModel();
            model.setToyImage(TOY_IMAGES[i]);
            model.setToyName(TOY_NAMES[i]);
            model.setToyPrice(TOY_PRICES[i]);
            model.setToyNum(0);
            toyList.add(model);
        }
    }
    
    private void findView(View view) {
        //add items button
        listview = view.findViewById(R.id.listview);
        
        //add place order button
        order = view.findViewById(R.id.btn_order);
        
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toyNumMark>0) {
                    Intent toyListIntent = new Intent(getActivity(), EditUserInfoActivity.class);
                    String toyInfo="";
                    int totallyPrice = 0;
                    
                    for (ToyModel model:toyList){
                        if (model.getToyNum() == 0){
                            continue;
                        }else {
                            toyInfo = toyInfo + model.getToyName() + " "+"$" +
                                    "" +
                                    "" +model.getToyPrice()+" "+model.getToyNum()+"\n";
                            totallyPrice = totallyPrice+ model.getToyPrice()*model.getToyNum();
                        }
                    }
    
                    toyInfo = toyInfo+"Totally Price: $" +totallyPrice;
                    
                    toyListIntent.putExtra(IntentString.ORDER_TOY_INFO,toyInfo);
                    startActivity(toyListIntent);
                }else{
                    Toast.makeText(getActivity(),"Selected is empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    private void initListView() {
        adapter = new ToyAdapter();
        
        listview.setAdapter(adapter);
    }
    
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    class ToyAdapter extends BaseAdapter {
        
        @Override
        public int getCount() {
            return toyList.size();
        }
        
        @Override
        public Object getItem(int position) {
            return toyList.get(position);
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_toy_item, null);
            final ImageView image = view.findViewById(R.id.toyImage);
            TextView name = view.findViewById(R.id.toyName);
            TextView price = view.findViewById(R.id.toyPrice);
            final TextView num = view.findViewById(R.id.toyNum);
            ImageButton removeBtn = view.findViewById(R.id.removeBtn);
            ImageButton addBtn = view.findViewById(R.id.addBtn);
            
            final ToyModel model = toyList.get(position);
            
            image.setImageResource(model.getToyImage());
            name.setText(model.getToyName());
            price.setText("$" + model.getToyPrice());
            num.setText(model.getToyNum() + "");
            
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (model.getToyNum() == 0) {
                        Toast.makeText(getActivity(),"Please select a toy!",Toast.LENGTH_SHORT).show();
                    } else {
                        int toyNum = model.getToyNum() - 1;
                        toyList.get(position).setToyNum(toyNum);
                        num.setText(model.getToyNum() + "");
                        toyNumMark--;
                    }
                }
            });
            
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int toyNum = model.getToyNum() + 1;
                    toyList.get(position).setToyNum(toyNum);
                    num.setText(model.getToyNum() + "");
                    toyNumMark++;
                }
            });
            
            return view;
        }
    }
}
