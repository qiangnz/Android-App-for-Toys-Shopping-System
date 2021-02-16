package com.example.sd6501_final_project_2173138.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sd6501_final_project_2173138.model.OrderModel;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    //database
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "toy.db";

    //expense transaction table
    private static final String TABLE_ORDER = "order_detail";
    public static final String ORDER_ID = "id";
    public static final String ORDER_INFO = "order_info";
    public static final String ORDER_USERNAME= "order_username";
    public static final String ORDER_ADDRESS = "order_address";
    
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //onCreate()
    @Override
    public void onCreate(SQLiteDatabase db) {

        // TransactionDetail_tb
        String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + "("
                + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ORDER_USERNAME + " TEXT,"
                + ORDER_ADDRESS + " TEXT,"
                + ORDER_INFO + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_ORDER);
    } //end of onCreate


    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);

    }

    public ArrayList<OrderModel> getOrders() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<OrderModel> orderList = new ArrayList<>();

        String query = "SELECT id, order_username, order_address, order_info FROM " + TABLE_ORDER;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            OrderModel model = new OrderModel();
            model.setOrderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            model.setOrderUsername(cursor.getString(cursor.getColumnIndex(ORDER_USERNAME)));
            model.setOrderAddress(cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS)));
            model.setToyInfo(cursor.getString(cursor.getColumnIndex(ORDER_INFO)));
            orderList.add(model);

        }
        db.close();
        return orderList;
    }


    //insert
    public long insertOrderDetails(String username,String address,String orderInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(ORDER_USERNAME, username);
        cValues.put(ORDER_ADDRESS, address);
        cValues.put(ORDER_INFO, orderInfo);
        long newRodID = db.insert(TABLE_ORDER, null, cValues);
        db.close();
        return newRodID;
    }

    //update
    public void updateListDetails(int orderID,
                                  String username,
                                  String address,
                                  String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(ORDER_ID, orderID);
        cValues.put(ORDER_USERNAME, username);
        cValues.put(ORDER_ADDRESS, address);
        cValues.put(ORDER_INFO, info);
        long newRodID = db.update(TABLE_ORDER, cValues,
                ORDER_ID + " =?",
                new String[]{String.valueOf(orderID)});
        db.close();

    }

    //delete
    public void deleteList(int orderID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_ORDER,
                ORDER_ID + " =?",
                new String[]{String.valueOf(orderID)});
        db.close();
    }

}
