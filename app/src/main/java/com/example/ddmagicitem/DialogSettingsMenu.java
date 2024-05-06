package com.example.ddmagicitem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DialogSettingsMenu extends DialogFragment {
    //setting tag for Log.d
    private static final String TAG = "Dialog Settings Menu";

    private TextView name, version, app;

    //setting up call for customAdapter class
    CustomAdapter customAdapter;


    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        //Alert box builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.main_settings, null);

        //adding resources
        name = dialogView.findViewById(R.id.aboutName);
        version = dialogView.findViewById(R.id.aboutAppV);
        app = dialogView.findViewById(R.id.aboutApp);
        final Button btnBack = dialogView.findViewById(R.id.btnBack);

        btnBack.setOnClickListener((View v) ->{
            dismiss();
        });



        builder.setView(dialogView).setMessage("About Me");

        return builder.create();
    }//End of onCreateDialog


}
