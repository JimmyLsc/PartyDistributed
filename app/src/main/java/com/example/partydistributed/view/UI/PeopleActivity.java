package com.example.partydistributed.view.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partydistributed.R;
import com.example.partydistributed.model.CRUD;
import com.example.partydistributed.model.PeopleAdaptor;
import com.example.partydistributed.utils.People;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PeopleActivity extends AppCompatActivity {

    private PeopleAdaptor peopleAdaptor;
    private List<People> peopleList;
    private RecyclerView peopleListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        peopleList = new ArrayList<>();

        Button addPeopleButton = findViewById(R.id.addPeopleButton2);
        addPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(PeopleActivity.this);

                new AlertDialog.Builder(PeopleActivity.this).setTitle("Name")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(et)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String input = et.getText().toString();
                                if (input.equals("")) {
                                    Toast.makeText(getApplicationContext(), "Name cannot be empty！" + input, Toast.LENGTH_LONG).show();
                                } else {

                                    People people = new People(input);
                                    Log.d(MainActivity.TAG, "create op");
                                    CRUD op = new CRUD(getApplicationContext());
                                    op.open();
                                    Log.d(MainActivity.TAG, "adding");
                                    op.addPeople(people);
                                    Log.d(MainActivity.TAG, "add successfully");
                                    op.close();
                                    Log.d(MainActivity.TAG, "close successfully");
                                    refreshListView();

                                    peopleAdaptor = new PeopleAdaptor(PeopleActivity.this, peopleList);
                                    Log.d(MainActivity.TAG, "adaptor created");
                                    peopleListView.setAdapter(peopleAdaptor);
                                    Log.d(MainActivity.TAG, "ListView updated");
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
//
            }
        });
        peopleAdaptor = new PeopleAdaptor(PeopleActivity.this, peopleList);

        peopleListView = (RecyclerView) findViewById(R.id.peopleList);
        peopleListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        refreshListView();
        peopleListView.setAdapter(peopleAdaptor);

    }


    public void refreshListView(){
        if (peopleList.size() > 0) {

            peopleList.clear();
        }
        Log.d(MainActivity.TAG, "size = 0");
        CRUD op = new CRUD(getApplicationContext());
        op.open();
        Log.d(MainActivity.TAG, "open successfully");
        peopleList.addAll(op.getAllPeople());
        Log.d(MainActivity.TAG, "get successfully");
        op.close();
    }
}