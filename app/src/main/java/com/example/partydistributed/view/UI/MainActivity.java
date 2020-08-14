package com.example.partydistributed.view.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.partydistributed.R;
import com.example.partydistributed.model.PeopleAdaptor;
import com.example.partydistributed.utils.People;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final public static String TAG = "myTest";




    private FloatingActionButton addActionButton;
    private FloatingActionButton addPeopleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActionButton = (FloatingActionButton) findViewById(R.id.addActionButton);
        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPageActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        addPeopleButton = (FloatingActionButton) findViewById(R.id.addPeopleButton);
        addPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
