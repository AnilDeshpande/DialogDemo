package com.codetutor.dialogdemo.ui.dialogs;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codetutor.dialogdemo.R;

import java.util.Calendar;

/**
 * Created by anildeshpande on 3/21/18.
 */

public class CustomDateAndTimePicker extends DialogFragment implements TimePicker.OnTimeChangedListener, DatePicker.OnDateChangedListener {


    View rootView;

    TimePicker timePicker;
    DatePicker datePicker;

    Button buttonSetDate;

    Calendar calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_date_time_picker,container);
        initUI();

        return rootView;
    }

    private void initUI(){
        timePicker = (TimePicker)rootView.findViewById(R.id.timePicker);
        datePicker = (DatePicker)rootView.findViewById(R.id.datePicker);
        buttonSetDate  = (Button)rootView.findViewById(R.id.buttonSetDate);

        datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),this);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        timePicker.setOnTimeChangedListener(this);

        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH)+
                                ", "+calendar.get(Calendar.HOUR_OF_DAY)+": "+calendar.get(Calendar.MINUTE);

                Toast.makeText(getActivity(),date,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
    }


}
