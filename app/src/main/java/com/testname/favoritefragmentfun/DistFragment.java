package com.testname.favoritefragmentfun;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class DistFragment extends Fragment {

    static final String EDIT_TEXT1="EDITEDTEXT1";
    static final String EDITTEXT_TEXT1="EDITEXTEXT1";
    String defaultString="0";

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dist_fragment,container, false);
    }//end onCreateView

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        SharedPreferences settings = getActivity().getPreferences(MODE_PRIVATE);



        String editTextString = settings.getString(EDIT_TEXT1, defaultString);
        String contentinEditText=settings.getString(EDITTEXT_TEXT1,defaultString);


        EditText tempEditText = getActivity().findViewById(R.id.editTextNumberDist);
        TextView tempTextView = getActivity().findViewById(R.id.DistTextView);

        tempEditText.setText(contentinEditText);
        tempTextView.setText(editTextString);

    }

}
