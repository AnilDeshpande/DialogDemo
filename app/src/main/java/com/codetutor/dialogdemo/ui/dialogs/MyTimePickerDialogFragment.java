package com.codetutor.dialogdemo.ui.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by anildeshpande on 3/21/18.
 */

public class MyTimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),this,hours,minute,false);
        return timePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        if (timePicker.is24HourView()){
            Toast.makeText(getActivity(),hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
        }else{
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE,minute);
            String amPM = calendar.get(Calendar.AM_PM)==Calendar.AM ? "AM": "PM";
            Toast.makeText(getActivity(),calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" "+amPM,Toast.LENGTH_SHORT).show();
        }



    }
}
