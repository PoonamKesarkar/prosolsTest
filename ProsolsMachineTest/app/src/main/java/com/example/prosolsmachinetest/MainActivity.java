package com.example.prosolsmachinetest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmpName, edtPhoneNo, edtAddress, edtEmialId;
    private Button btnAdd, btnDelete,getAllData;
    private Toolbar toolbar;
    private TextView txtTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        edtEmpName = findViewById(R.id.edtEmpName);
        edtPhoneNo = findViewById(R.id.edtPhoneNo);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmialId = findViewById(R.id.edtEmialId);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        getAllData = findViewById(R.id.getAllData);
        txtTextView = findViewById(R.id.txtTextView);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Employee Information");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidations();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmpData();
            }
        });

    }

    private void getEmpData() {
        class GetTasks extends AsyncTask<Void, Void, List<EmpData>> {

            @Override
            protected List<EmpData> doInBackground(Void... voids) {
                List<EmpData> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .empDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<EmpData> tasks) {
                super.onPostExecute(tasks);
                String text="";
                if(tasks.size()>0) {
                    for (int i = 0; i < tasks.size(); i++) {
                        text = text + tasks.get(i).toString();
                    }

                    txtTextView.setText(text);
                }

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }


    private void deleteData() {
        Intent i= new Intent(this,UpdateActivity.class);
        startActivity(i);
    }

    private void addData() {
        final String name = edtEmpName.getText().toString();
        final String phoneNo = edtPhoneNo.getText().toString();
        final String address = edtAddress.getText().toString();
        final String email = edtEmialId.getText().toString();
        class SaveEmpData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                EmpData empData = new EmpData();
                empData.setName(name);
                empData.setAddress(address);
                empData.setEmailId(email);
                empData.setPhoneNo(phoneNo);
                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .empDao()
                        .insert(empData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Insert successfully", Toast.LENGTH_LONG).show();
            }
        }

        SaveEmpData st = new SaveEmpData();
        st.execute();
    }


    private void checkValidations() {
        if (edtEmpName.getText().toString().equals("")) {
            Toast.makeText(this, "Plesae enter name", Toast.LENGTH_SHORT).show();
        } else if (edtPhoneNo.getText().toString().equals("")) {
            Toast.makeText(this, "Plesae enter phone no", Toast.LENGTH_SHORT).show();
        } else if (edtAddress.getText().toString().equals("")) {
            Toast.makeText(this, "Plesae enter address", Toast.LENGTH_SHORT).show();
        } else if (edtEmialId.getText().toString().equals("")) {
            Toast.makeText(this, "Plesae enter email id", Toast.LENGTH_SHORT).show();
        } else {
            addData();
        }
    }
}
