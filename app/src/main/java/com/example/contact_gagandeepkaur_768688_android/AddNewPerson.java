package com.example.contact_gagandeepkaur_768688_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewPerson extends AppCompatActivity {
    EditText etFirstName, etLastName, etEmail, etAddress, etPhone;
    String fName, lName, Email, address, phone;
    private DatabaseHelper mDatabaseHelper;
    private PersonModel personModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_Email);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        personModel = (PersonModel) intent.getSerializableExtra(MainActivity.SELECTED_PERSON);

        if (personModel != null) {
            etFirstName.setText(personModel.getfName());
            etLastName.setText(personModel.getlName());
            etAddress.setText(personModel.getAddress());
            etEmail.setText(personModel.getEmail());
            etPhone.setText(personModel.getPhone());
        }

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName = etFirstName.getText().toString().trim();
                lName = etLastName.getText().toString().trim();
                Email = etEmail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                address = etAddress.getText().toString().trim();


                if (validations()) {
                    if (personModel != null) {

                        //update existing person
                        if (mDatabaseHelper.updatePerson(personModel.getId(), fName, lName, Email, phone, address)) {
                            Toast.makeText(AddNewPerson.this, fName + " success", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else
                            Toast.makeText(AddNewPerson.this, "Error  " + fName, Toast.LENGTH_SHORT).show();
                    } else {

                        //add new person
                        if (mDatabaseHelper.addPerson(fName, lName, Email, phone, address)) {
                            Toast.makeText(AddNewPerson.this, fName + " added new", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else
                            Toast.makeText(AddNewPerson.this, "not added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    private boolean validations() {

        if (fName.isEmpty()) {
            etFirstName.setError("First name is required");
            etFirstName.requestFocus();
            return false;
        } else {
            if (lName.isEmpty()) {
                etLastName.setError("Last name is required");
                etLastName.requestFocus();
                return false;

            } else {
                if (Email.isEmpty()) {
                    etEmail.setError("Email Required");
                    etEmail.requestFocus();
                    return false;
                } else {
                    if (address.isEmpty()) {
                        etAddress.setError("Adress required");
                        etAddress.requestFocus();
                        return false;
                    } else {
                        if (phone.isEmpty()) {
                            etPhone.setError("number needed");
                            etPhone.requestFocus();
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }

        }
    }
}

