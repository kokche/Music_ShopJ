package com.example.musicshopj;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int countcurrentQuantity = 0;
    Spinner spiner;
    ArrayAdapter spinerAdapter;
    String goodsName;
    HashMap goodsMap;
    EditText nameEditText;
    int price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.editText);

        createSpiner();
        createMap();
    }

    private void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar",500);
        goodsMap.put("piano",800);
        goodsMap.put("drums",1500);
    }
    void createSpiner()
    {
        spiner=findViewById(R.id.spinner);
        spiner.setOnItemSelectedListener(this);
        ArrayList spinerArray = new ArrayList();

        spinerArray.add("guitar");
        spinerArray.add("piano");
        spinerArray.add("drums");

        spinerAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinerArray);
        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(spinerAdapter);
    }

    public void incCount(View view) {
        TextView currentQuantity = findViewById(R.id.currentQuantity);
        countcurrentQuantity++;
        currentQuantity.setText(countcurrentQuantity+"");
        TextView curentOrderprice = findViewById(R.id.curentOrderPrice);
        price = (int)goodsMap.get(goodsName)*countcurrentQuantity;
        curentOrderprice.setText("" + price);
    }

    public void decreaseQuantity(View view) {
        TextView currentQuantity = findViewById(R.id.currentQuantity);
        if(countcurrentQuantity>0)
            countcurrentQuantity--;
        currentQuantity.setText(countcurrentQuantity+"");
        TextView curentOrderprice = findViewById(R.id.curentOrderPrice);
        price = (int)goodsMap.get(goodsName)*countcurrentQuantity;
        curentOrderprice.setText("" + price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ImageView image =findViewById(R.id.imageView3);
        goodsName = spiner.getSelectedItem().toString();

        if(goodsName.equals("guitar"))
            image.setImageResource(R.drawable.acustic);
        if (goodsName.equals("piano"))
            image.setImageResource(R.drawable.piano);
        if (goodsName.equals("drums"))
            image.setImageResource(R.drawable.drums);
        price = (int)goodsMap.get(goodsName)*countcurrentQuantity;
        TextView curentOrderprice = findViewById(R.id.curentOrderPrice);
        curentOrderprice.setText("" + price);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCarT(View view) {
        Order order = new Order();
        order.Name= nameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = countcurrentQuantity;
        order.price = price;

        Intent orderIntent = new Intent(MainActivity.this,OrderActivity.class);
        orderIntent.putExtra("Name",order.Name);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("price",order.price);
        startActivity(orderIntent);
    }
}
