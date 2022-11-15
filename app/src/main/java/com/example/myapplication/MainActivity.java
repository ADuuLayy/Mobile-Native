package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner p_type;
    EditText bed_no;
    EditText bath_no;
    EditText date;
    EditText time;
    EditText m_rent;
    Spinner f_type;
    EditText note;
    EditText rname;
    Button btn_Add;
    private String PropertyType, BedroomNo, BathroomNo, Date, Time, MonthlyRent, FurnitureType, Note, ReporterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner f_type = (Spinner) findViewById(R.id.f_type);
        Spinner p_type = (Spinner) findViewById(R.id.p_type);
        Spinner bed_no = (Spinner) findViewById(R.id.bed_no);
        Spinner bath_no = (Spinner) findViewById(R.id.bath_no);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        m_rent = findViewById(R.id.m_rent);
        note = findViewById(R.id.note);
        rname = findViewById(R.id.rname);
        btn_Add = findViewById(R.id.btn_Add);

        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Furniture_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        f_type.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.PropertyType_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        p_type.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.BedNo_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bed_no.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.BathNo_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bath_no.setAdapter(adapter3);

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDateDialog(date);
            }
        });

        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showTimeDialog(time);
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Date=date.getText().toString();
                Time=time.getText().toString();
                MonthlyRent=m_rent.getText().toString();
                Note=note.getText().toString();
                ReporterName=rname.getText().toString();

                if (Date.matches("") || Time.matches("") || MonthlyRent.matches("")  || ReporterName.matches("")){
                    alert("Make sure You have filled Date and Time and Monthly Rent and Reporter Name");
                }
                else {

                    alert1("");
                }
            }
        });
    }


    private void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dlg.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void alert1(String message){

        LocalDate DateToday=LocalDate.now();
        String pattern1="yyyy / MM / dd";
        DateTimeFormatter formatDateToday=DateTimeFormatter.ofPattern(pattern1);

        LocalTime TimeNow=LocalTime.now();
        String pattern2="hh:mm a";
        DateTimeFormatter formatTimeNow=DateTimeFormatter.ofPattern(pattern2);
        AlertDialog dlg1 = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirmation")
                .setMessage("Do you want to add data at " + formatDateToday.format(DateToday) +"  " + formatTimeNow.format(TimeNow))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alert("Successfully Added");
                        date.setText("");
                        time.setText("");
                        m_rent.setText("");
                        note.setText("");
                        rname.setText("");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert("Data didn't add");
                    }
                })
                .create();
        dlg1.show();
    }

    private void showTimeDialog(EditText time){
        Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    private void showDateDialog(EditText date){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(MainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }



}


