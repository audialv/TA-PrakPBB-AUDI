package com.fariz.travel.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fariz.travel.R;
import com.fariz.travel.database.DatabaseHelper;
import com.fariz.travel.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class OrderHotelActivity extends AppCompatActivity {

    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    public String sAsal, sTujuan, sTanggal, sDewasa, sAnak, sDurasi;
    Calendar newCalendar = Calendar.getInstance();
    Spinner spDurasi;
    int id_book;
    String email;
    SessionManager session;
    String type = "hotel";
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    Button bt_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_hotel);
        etTanggal = findViewById(R.id.dpTanggal);
        spDurasi = findViewById(R.id.et_durasi);
        bt_order = findViewById(R.id.order_btn_book);

        setDateTimeField();


        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);

        dbHelper = new DatabaseHelper(OrderHotelActivity.this);
        db = dbHelper.getReadableDatabase();

        final String[] durasi = {"1", "2", "3", "4", "5","6","7"};


        ArrayAdapter<CharSequence> adapterAnak = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, durasi);
        adapterAnak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDurasi.setAdapter(adapterAnak);

        spDurasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sDurasi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDB();
            }
        });
    }

    private void insertDB() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String hotelname = sh.getString("hotel", "");
        String hotelcity = sh.getString("city", "");
        Integer hotelprice = sh.getInt("price", 0);
        Integer position = sh.getInt("position", 0);

        Log.d("position", position.toString());
        Log.d("name", hotelname);
        Log.d("hotelcity", hotelcity);
        Log.d("hotelprice", hotelprice.toString());

        Integer TotalHarga = hotelprice * Integer.valueOf(sDurasi);
        Log.d("totoalll",TotalHarga.toString());



        AlertDialog dlg = new AlertDialog.Builder( OrderHotelActivity.this).setTitle("booking hotel?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            db.execSQL("INSERT INTO TB_BOOK (asalxx, tujuan, tanggal, dewasa, anak,hotel,city, type) VALUES ('" +
                                    "hotel" + "','" +
                                    hotelname + "','" +
                                    sTanggal + "','" +
                                    hotelcity + "','" +
                                    Integer.valueOf(sDurasi) + "','"+
                                    hotelname +"','"+
                                    hotelcity+"','"+ "hotel" +"');");
                            cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                            cursor.moveToLast();
                            if (cursor.getCount() > 0) {
                                cursor.moveToPosition(0);
                                id_book = cursor.getInt(0);
                            }
                            db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_dewasa, harga_anak, harga_total) VALUES ('" +
                                    email + "','" +
                                    id_book + "','" +
                                    TotalHarga + "','" +
                                    TotalHarga + "','" +
                                    TotalHarga + "');");
                            Toast.makeText(OrderHotelActivity.this, "Booking berhasil", Toast.LENGTH_LONG).show();
                            finish();
                        } catch (Exception e) {
                            Toast.makeText(OrderHotelActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Tidak",null).create();
        dlg.show();


    }

    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}