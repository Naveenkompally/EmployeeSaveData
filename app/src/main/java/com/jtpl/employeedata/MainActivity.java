package com.jtpl.employeedata;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress;
    private Spinner spinnerDepartment;
    private RadioGroup radioGroupGender;
    private Switch switchFresher;
    private Button buttonSave , buttonViewData,buttonDiscard;

    private EmployeeDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        switchFresher = findViewById(R.id.switchFresher);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDiscard = findViewById(R.id.buttonDiscard);
        buttonViewData = findViewById(R.id.viewData);

        dbHelper = new EmployeeDatabaseHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departments_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });

        buttonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
        buttonViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmployeeListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveEmployee() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String department = spinnerDepartment.getSelectedItem().toString();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender.getText().toString();
        boolean isFresher = switchFresher.isChecked();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Name cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            editTextAddress.setError("Address cannot be empty");
            return;
        }

        boolean isInserted = dbHelper.insertEmployee(name, address, department, gender, isFresher);
        if (isInserted) {
            Toast.makeText(this, "Employee saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, EmployeeListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error saving employee", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        editTextName.setText("");
        editTextAddress.setText("");
        spinnerDepartment.setSelection(0);
//        radioGroupGender.clearCheck();
        switchFresher.setChecked(false);
    }
}