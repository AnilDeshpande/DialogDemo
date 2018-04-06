package com.codetutor.dialogdemo.ui.activities;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codetutor.dialogdemo.R;
import com.codetutor.dialogdemo.ui.dialogs.CustomDateAndTimePicker;
import com.codetutor.dialogdemo.ui.dialogs.MyDatePickerDialogFragment;
import com.codetutor.dialogdemo.ui.dialogs.MyTimePickerDialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSimpleAlert, buttonDatePicker, buttonTimepPicker, buttonCustomDateTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSimpleAlert = (Button)findViewById(R.id.buttonSimpleAlert);
        buttonDatePicker = (Button)findViewById(R.id.buttonDatePicker);
        buttonTimepPicker = (Button)findViewById(R.id.buttonTimePicker);
        buttonCustomDateTimePicker = (Button)findViewById(R.id.buttonCustomDateTimePicker);

        buttonSimpleAlert.setOnClickListener(this);
        buttonDatePicker.setOnClickListener(this);
        buttonTimepPicker.setOnClickListener(this);
        buttonCustomDateTimePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSimpleAlert: showAlertDialog(); break;
            case R.id.buttonDatePicker: showDatePickerDialog();break;
            case R.id.buttonTimePicker: showTimePickerDialog(); break;
            case R.id.buttonCustomDateTimePicker: showCustomDateTimePicker(); break;
        }
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Alert") .setMessage("Do you want to continue?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Yes clicked",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"No clicked",Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showDatePickerDialog(){
        MyDatePickerDialogFragment datePickerDialogFragment = new MyDatePickerDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("TODAY",Calendar.getInstance());
        datePickerDialogFragment.setArguments(bundle);
        datePickerDialogFragment.show(getFragmentManager(),"datepicker");
    }

    private void showTimePickerDialog(){
        MyTimePickerDialogFragment timePickerDialogFragment = new MyTimePickerDialogFragment();
        timePickerDialogFragment.show(getFragmentManager(),"timepicker");

    }

    private void showCustomDateTimePicker(){
        CustomDateAndTimePicker customPicker = new CustomDateAndTimePicker();
        customPicker.show(getFragmentManager(),"customPicker");
    }



}
