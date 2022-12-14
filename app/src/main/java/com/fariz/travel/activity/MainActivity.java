package com.fariz.travel.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fariz.travel.R;
import com.fariz.travel.activity.api.listapi;
import com.fariz.travel.adapter.AlertDialogManager;
import com.fariz.travel.session.SessionManager;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    MaterialButton btnLogout, btorderfood;
    Button orderfood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        btnLogout = findViewById(R.id.out);
        btorderfood = findViewById(R.id.order_food);

        btorderfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, listapi.class);
                startActivity(i);
                
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });
    }

    public void profileMenu(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void historyMenu(View v) {
        Intent i = new Intent(this, HistoryActivity.class);
        startActivity(i);
    }

    public void bookKereta(View v) {
        Intent i = new Intent(this, BookKeretaActivity.class);
        startActivity(i);
    }

    public void bookHotel(View v) {
        Intent i = new Intent(this, BookHotelActivity.class);
        startActivity(i);
    }
}