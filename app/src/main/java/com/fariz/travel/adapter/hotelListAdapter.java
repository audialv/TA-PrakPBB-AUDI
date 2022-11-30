package com.fariz.travel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fariz.travel.R;

public class hotelListAdapter extends ArrayAdapter {
    private String[] hotelname;
    private String[] hotelcity;
    private Integer[] hotelprice;
    private Integer[] hotelimageid;
    private Activity context;


    public hotelListAdapter(Activity context, String[] hotelname,String[] hotelcity, Integer[] hotelprice,Integer[] hotelimageid) {
        super(context, R.layout.row_item, hotelname);
        this.context = context;
        this.hotelname = hotelname;
        this.hotelcity = hotelcity;
        this.hotelprice = hotelprice;
        this.hotelimageid = hotelimageid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        row = inflater.inflate(R.layout.row_item, null, true);

        TextView tv_hotelname = (TextView) row.findViewById(R.id.tv_hotel);
        TextView tv_city = (TextView) row.findViewById(R.id.tv_city);
        ImageView imageflag = (ImageView) row.findViewById(R.id.imageViewFlag);
        TextView tv_price = (TextView) row.findViewById(R.id.tv_price);

        tv_price.setText(hotelprice[position].toString());
        tv_hotelname.setText(hotelname[position]);
        tv_city.setText(hotelcity[position]);
        imageflag.setImageResource(hotelimageid[position]);

        return row;
    }
}
