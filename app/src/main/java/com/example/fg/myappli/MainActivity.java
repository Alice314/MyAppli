package com.example.fg.myappli;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final String[] mItems = getResources().getStringArray(R.array.academy);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item,mItems);
        adapter.setDropDownViewResource(android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String name = adapterView.getItemAtPosition(position).toString();
                SubjectsBean subjectsBean = new SubjectsBean();
                double[]datas = new double[]{subjectsBean.getFirstData()[position],subjectsBean.getSecondData()[position]
                ,subjectsBean.getThirdData()[position]};
                CircleView circleView = new CircleView(getApplicationContext(),null);
                circleView.setData(datas);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}


