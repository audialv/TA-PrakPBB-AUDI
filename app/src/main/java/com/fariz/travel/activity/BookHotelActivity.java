package com.fariz.travel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fariz.travel.R;
import com.fariz.travel.adapter.hotelListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookHotelActivity extends AppCompatActivity {


    private ListView listView;
    TextView tvx;



    private String hotelname[] = {
            "The Oberoi",
            "Hotel Tentrem",
            "Jambuluwuk",
            "Hotel Nueve",
            "Fave Hotel",
            "Harper",
            "Grand inna",
            "Marriot Hotel",
            "The Phoenix",
            "Cavinton Hotel"
    };

    private String hotelcity[] = {
            "Jakarta",
            "Jakarta",
            "Semarang",
            "Semarang",
            "Purwokerto",
            "Purwokerto",
            "Yogyakarta",
            "Yogyakarta",
            "Surabaya",
            "Surabaya"
    };

    private Integer hotelprice[] = {
            450000,
            500000,
            370000,
            430000,
            640000,
            900000,
            700000,
            800000,
            650000,
            560000

    };

    private Integer hotelimageid[] = {
            R.drawable.satu,
            R.drawable.dua,
            R.drawable.tiga,
            R.drawable.empat,
            R.drawable.lima,
            R.drawable.enam,
            R.drawable.tujuh,
            R.drawable.delapan,
            R.drawable.sembilan,
            R.drawable.sepuluh

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

        ListView listView=(ListView)findViewById(R.id.list);
        hotelListAdapter customCountryList = new hotelListAdapter(this, hotelname, hotelcity, hotelprice, hotelimageid);
        listView.setAdapter(customCountryList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {












                Intent intent = new Intent(listView.getContext(),OrderHotelActivity.class);
                listView.getContext().startActivity(intent);



                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putInt("position", position);
                myEdit.putString("hotel", hotelname[position]);
                myEdit.putString("city", hotelcity[position]);
                myEdit.putInt("price", hotelprice[position]);
                myEdit.commit();




                    }
        });

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbKrl);
        toolbar.setTitle("List Hotel");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}