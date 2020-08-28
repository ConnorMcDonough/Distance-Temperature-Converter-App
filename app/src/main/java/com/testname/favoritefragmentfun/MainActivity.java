package com.testname.favoritefragmentfun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private DistFragment distFragment;
    private TempFragment tempFragment;

    RadioGroup radioGroup;
    RadioButton radioButtonFtoC;
    RadioButton radioButtonCtoF;
    RadioButton radioButtonMiletoKm;
    RadioButton radioButtonKmtoMile;
    TextView textTempView;
    TextView textDistView;
    EditText tempEditText;
    EditText distanceEditText;

    String currentState="";
    
    static final String EDIT_TEXT="EDITEDTEXT";
    static final String EDITTEXT_TEXT="EDITTEXTTEXT";
    static final String EDIT_TEXT1="EDITEDTEXT1";
    static final String EDITTEXT_TEXT1="EDITEXTEXT1";
    static final String CURRENT_STATE="CURRENTSTATE";

    String editTextString="";
    String contentinEditText="";
    String editTextString1="";
    String contentinEditText1="";

    String currentNumberString="";
    double currentNumber=0;
    double convertedNumber=0;
    String defaultString="0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distFragment = new DistFragment();
        tempFragment = new TempFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        currentState=settings.getString(CURRENT_STATE,"Tempuratures");

        editTextString = settings.getString(EDIT_TEXT, defaultString);
        contentinEditText=settings.getString(EDITTEXT_TEXT,defaultString);
        editTextString1=settings.getString(EDIT_TEXT1,defaultString);
        contentinEditText1=settings.getString(EDITTEXT_TEXT1,defaultString);
        
        
        
        //begin placement of the fragment
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

        //set dist as the default fragment
        //we will use value 0 for the showingFragment (default)
        fragmentTransaction.add(R.id.placeHolderLayout, distFragment);
        //commit the change
        fragmentTransaction.commit();
    }//end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

        if(item.getItemId() == R.id.menu_dist) {
            fragmentTransaction.replace(R.id.placeHolderLayout,distFragment);
            currentState="Distance";
        }
        else if(item.getItemId() == R.id.menu_temp) {
            fragmentTransaction.replace(R.id.placeHolderLayout,tempFragment);
            currentState="Tempuratures";
        }
        else {
            //default
            return super.onContextItemSelected(item);
        }

        //don't forget to commit!!!
        fragmentTransaction.commit();
        return true;
    }//end onOptionsItemSelected

    public void radioClicked(View view) {
        radioGroup=findViewById(R.id.radioGroup);
        radioButtonCtoF=findViewById(R.id.CtoF);
        radioButtonFtoC=findViewById(R.id.FtoC);
        radioButtonKmtoMile=findViewById(R.id.KtoM);
        radioButtonMiletoKm=findViewById(R.id.MtoK);
        textTempView=findViewById(R.id.TempTextView);
        textDistView=findViewById(R.id.DistTextView);
        tempEditText=findViewById(R.id.editTextNumberTemp);
        distanceEditText=findViewById(R.id.editTextNumberDist);

        String numberToConvert="";
        double currentNumber=0;
        double convertedNumber=0;

        if((radioButtonFtoC!=null)&&(radioButtonFtoC.isChecked())) {
            numberToConvert=tempEditText.getText().toString();
            currentNumber=Double.parseDouble(numberToConvert);
            convertedNumber=(currentNumber-32.0)*(.55555555555555555);
            textTempView.setText(convertedNumber + " Degrees Celsius");
        }
        else if((radioButtonCtoF!=null)&&(radioButtonCtoF.isChecked())) {
            numberToConvert=tempEditText.getText().toString();
            currentNumber=Double.parseDouble(numberToConvert);
            convertedNumber=(currentNumber*(1.8))+32.0;
            textTempView.setText(convertedNumber + " Degrees Fahrenheit");
        }
        else if((radioButtonMiletoKm!=null)&&(radioButtonMiletoKm.isChecked())) {
            numberToConvert=distanceEditText.getText().toString();
            currentNumber=Double.parseDouble(numberToConvert);
            convertedNumber=(currentNumber*1.609);
            textDistView.setText(convertedNumber + " km");
        }
        else if((radioButtonKmtoMile!=null)&&(radioButtonKmtoMile.isChecked())) {
            numberToConvert=distanceEditText.getText().toString();
            currentNumber=Double.parseDouble(numberToConvert);
            convertedNumber=(currentNumber/1.609);
            int chek=(int) (convertedNumber*10000); //rounds the Decimal places
            convertedNumber=(chek/10000.0);
            textDistView.setText(convertedNumber + " mi");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        textTempView=findViewById(R.id.TempTextView);
        textDistView=findViewById(R.id.DistTextView);

        tempEditText=findViewById(R.id.editTextNumberTemp);
        distanceEditText=findViewById(R.id.editTextNumberDist);

        if(textTempView !=null && tempEditText!= null) {
            outState.putString(EDIT_TEXT, textTempView.getText().toString());
            outState.putString(EDITTEXT_TEXT, tempEditText.getText().toString());
            outState.putString(CURRENT_STATE,"Tempuratures");

            textTempView.setText(editTextString);
            tempEditText.setText(contentinEditText);
        }

        if(textDistView!=null && distanceEditText!= null) {
            outState.putString(EDIT_TEXT1, textDistView.getText().toString());
            outState.putString(EDITTEXT_TEXT1, distanceEditText.getText().toString());
            outState.putString(CURRENT_STATE,"Distance");
        }
    }//end onSaveInstanceState

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        textTempView=findViewById(R.id.TempTextView);
        textDistView=findViewById(R.id.DistTextView);
        
        tempEditText=findViewById(R.id.editTextNumberTemp);
        distanceEditText=findViewById(R.id.editTextNumberDist);

        Toast.makeText(this, currentState, Toast.LENGTH_SHORT).show();

        if(textTempView !=null && tempEditText!= null) {
            editTextString = savedInstanceState.getString(EDIT_TEXT);
            contentinEditText = savedInstanceState.getString(EDITTEXT_TEXT);
            currentState= savedInstanceState.getString(CURRENT_STATE);

            textTempView.setText(editTextString);
            tempEditText.setText(contentinEditText);
        }
        if(textDistView!=null && distanceEditText!= null){
            editTextString1 = savedInstanceState.getString(EDIT_TEXT1);
            contentinEditText1 = savedInstanceState.getString(EDITTEXT_TEXT1);
            currentState= savedInstanceState.getString(CURRENT_STATE);

            textDistView.setText(editTextString1);
            distanceEditText.setText(contentinEditText1);}
    }//end onRestoreInstanceState

    @Override
    protected void onPause() {
        super.onPause();

        textTempView=findViewById(R.id.TempTextView);//tempView
        textDistView=findViewById(R.id.DistTextView);//textViewDistance

        tempEditText=findViewById(R.id.editTextNumberTemp);//TempEditText
        distanceEditText=findViewById(R.id.editTextNumberDist);//DistanceEditText

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        if(textTempView !=null && tempEditText!= null) {
            editor.putString(EDIT_TEXT, textTempView.getText().toString());
            editor.putString(EDITTEXT_TEXT, tempEditText.getText().toString());
            editor.putString(CURRENT_STATE,"Tempuratures");
        }

        if(textDistView!=null && distanceEditText!= null) {
            editor.putString(EDIT_TEXT1, textDistView.getText().toString());
            editor.putString(EDITTEXT_TEXT1, distanceEditText.getText().toString());
            editor.putString(CURRENT_STATE,"Distance");
        }

        editor.commit();
    }//end onPause
    
    
    
}
