package com.testname.favoritefragmentfun;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class TempFragment extends androidx.fragment.app.Fragment {

    static final String EDIT_TEXT="EDITEDTEXT";
    static final String EDITTEXT_TEXT="EDITTEXTTEXT";
    String defaultString="0";


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.temp_fragment,container, false);
    }//end onCreateView


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        SharedPreferences settings = getActivity().getPreferences(MODE_PRIVATE);



        String editTextString = settings.getString(EDIT_TEXT, defaultString);
        String contentinEditText=settings.getString(EDITTEXT_TEXT,defaultString);


        EditText tempEditText = getActivity().findViewById(R.id.editTextNumberTemp);
        TextView tempTextView = getActivity().findViewById(R.id.TempTextView);

        tempEditText.setText(contentinEditText);
        tempTextView.setText(editTextString);

    }


}
