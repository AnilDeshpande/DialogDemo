package com.codetutor.dialogdemo.ui.dialogs;

import android.app.Dialog;
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
import android.widget.Toast;

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
    View listViewContainer;
    ListView listViewHobbies;
    Button buttonDone;

    List<String> hobbies;
    List<String> selectedHobbies = new ArrayList<String>();

    Context context;

    private HobbiesSelectionListener hobbiesSelectionListener;

    public interface HobbiesSelectionListener{
        public void onHobbiesSelected(List<String> hobbies);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Holo_Light_Dialog);
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Dialog);
        //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
        //setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Holo_Light);
    }

    public void setHobbiesSelectionListener(HobbiesSelectionListener hobbiesSelectionListener){
        this.hobbiesSelectionListener=hobbiesSelectionListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_select_hobby));
        rootView = inflater.inflate(R.layout.dialog_hobby,container,false);
        initUI();
        return rootView;
    }

    private void initUI(){

        listViewContainer = rootView.findViewById(R.id.listViewContainer);
        listViewHobbies = (ListView)rootView.findViewById(R.id.listViewHobby);
        buttonDone = (Button)rootView.findViewById(R.id.buttonDone);
        listViewHobbies.setOnItemClickListener(this);
        buttonDone.setOnClickListener(this);
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
            case R.id.buttonDone:
                handleDone();
                break;
        }
    }

    private void handleDone(){
        if(selectedHobbies!=null && selectedHobbies.size()>0){
            if(hobbiesSelectionListener!=null){
                hobbiesSelectionListener.onHobbiesSelected(selectedHobbies);
                dismiss();
            }
        }else{
            Toast.makeText(context,"Select a hobby",Toast.LENGTH_SHORT).show();
        }
    }

    private void addHobby(String hobby){
        DialogDemoApp.addHobby(hobby);
        ArrayAdapter<String> hobbyAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_multiple_choice, DialogDemoApp.getHobbies());
        listViewHobbies.setAdapter(hobbyAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SparseBooleanArray checkedPositions = ((ListView)parent).getCheckedItemPositions();
        selectedHobbies.clear();
        for(int i=0;i<checkedPositions.size();i++){
            if (checkedPositions.valueAt(i)){
                selectedHobbies.add(DialogDemoApp.getHobbies().get(checkedPositions.keyAt(i)));
            }
        }

    }
}
