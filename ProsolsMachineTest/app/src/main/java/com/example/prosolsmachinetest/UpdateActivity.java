package com.example.prosolsmachinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
private EditText edtId;
private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtId=findViewById(R.id.edtId);
        btnDelete=findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtId.getText().toString().equals("")){
                    Toast.makeText(UpdateActivity.this, "Plz enter id", Toast.LENGTH_SHORT).show();
                }else{
                    deleteData();
                }
            }
        });
    }

    private void deleteData() {

    }
}
