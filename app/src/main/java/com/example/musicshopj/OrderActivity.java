package com.example.musicshopj;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class OrderActivity extends AppCompatActivity {
    String[] addresses={"kokche12@gmail.com"};String subject="Order from Music Shop",textOfEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //setTitle(R.string.Your_Oder);
        Intent orderIntent = getIntent();

        String name = orderIntent.getStringExtra("Name");
        String goodsName = orderIntent.getStringExtra("goodsName");
        int quantity = orderIntent.getIntExtra("quantity",0);
        int Orderprice = orderIntent.getIntExtra("price",0);
        int price= quantity != 0 ? Orderprice/quantity : 0;
        TextView orderTexts = findViewById(R.id.orderText);
        textOfEmail= "Customer name: " + name +"\n"+ "Goods name: " + goodsName+"\n"+ "Quantity: " +quantity
                +"\n"+"Price: "+price+ "\n" + "Order price: " + Orderprice;
        orderTexts.setText(textOfEmail);
    }
    public void sendOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textOfEmail);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);

    }
}
