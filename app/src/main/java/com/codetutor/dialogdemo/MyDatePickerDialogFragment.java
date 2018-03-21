package com.codetutor.dialogdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by anildeshpande on 3/21/18.
 */

public class MyDatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendar=(Calendar) getArguments().getSerializable("TODAY");
        if(calendar==null){
            calendar= Calendar.getInstance();
        }
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month,day);

        Toast.makeText(getActivity(),year+"/"+month+"/"+day,Toast.LENGTH_LONG).show();
    }
}
