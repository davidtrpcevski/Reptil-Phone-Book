package com.david.reptil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
            String storeName = getIntent().getStringExtra("store_name");
            String streetName = getIntent().getStringExtra("street_name");
            String number1 = getIntent().getStringExtra("number1");
            String number2 = getIntent().getStringExtra("number2");
            String liceName = getIntent().getStringExtra("lice_name");


        setDetails(storeName, streetName, number1, number2, liceName);
    }


    @SuppressLint("SetTextI18n")
    private void setDetails(String storeName, String streetName, String number1, String number2, String liceName){
        TextView store = findViewById(R.id.storeName);
        TextView street = findViewById(R.id.streetName);
        TextView numerouno = findViewById(R.id.number1);
        TextView numerodos = findViewById(R.id.number2);
        TextView lice = findViewById(R.id.liceName);

        store.setText("Продавница: " + storeName);
        street.setText("Улица: " + streetName);
        numerouno.setText("Фиксен: " + number1);
        numerodos.setText("Мобилен: " + number2);
        lice.setText("Лице за контакт: " + liceName);
    }

}




