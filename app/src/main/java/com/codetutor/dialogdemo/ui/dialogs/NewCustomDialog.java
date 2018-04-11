package com.codetutor.dialogdemo.ui.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.codetutor.dialogdemo.DialogDemoApp;
import com.codetutor.dialogdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anildeshpande on 4/10/18.
 */

public class NewCustomDialog extends DialogFragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    public static final String TAG = NewCustomDialog.class.getSimpleName();

    View rootView;
    View addContainer;
    ListView listViewHobbies;
    EditText editTextHobby;
    Button buttonAdd,buttonCancel;

    List<String> hobbies;
    List<String> selectedHobbies = new ArrayList<String>();

    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_hobby,container,false);
        initUI();
        return rootView;
    }

    private void initUI(){
        addContainer = rootView.findViewById(R.id.addContainer);
        listViewHobbies = (ListView)rootView.findViewById(R.id.listViewHobby);
        editTextHobby = (EditText)rootView.findViewById(R.id.editTextHobby);
        buttonAdd = (Button)rootView.findViewById(R.id.buttonAdd);
        buttonCancel = (Button)rootView.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        listViewHobbies.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity().getApplicationContext();
        bindUIData();
    }

    private void bindUIData(){
        ArrayAdapter<String> hobbyAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_multiple_choice, DialogDemoApp.getHobbies());
        listViewHobbies.setAdapter(hobbyAdapter);
        listViewHobbies.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewHobbies.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd: addHobby(editTextHobby.getText().toString()); break;
            case R.id.buttonCancel:addContainer.setVisibility(View.GONE);
                                   listViewHobbies.setVisibility(View.VISIBLE);
                                   break;
        }
    }

    private void addHobby(String hobby){
        DialogDemoApp.addHobby(hobby);
        ArrayAdapter<String> hobbyAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_multiple_choice, DialogDemoApp.getHobbies());
        listViewHobbies.setAdapter(hobbyAdapter);
        addContainer.setVisibility(View.GONE);
        listViewHobbies.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, ""+position);
        if(DialogDemoApp.getHobbies().get(position).equals("None")){
            addContainer.setVisibility(View.VISIBLE);
            listViewHobbies.setVisibility(View.GONE);
        }else {
            selectedHobbies.add(DialogDemoApp.getHobbies().get(position));
        }
    }
}
