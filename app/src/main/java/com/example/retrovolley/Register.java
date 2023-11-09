package com.example.retrovolley;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText editTextTglLahir, etUsername, etFullName, etEmail, etAddress, etPhoneNumber, etPassword;
    private Spinner spinnerGender, spinnerReligion;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextTglLahir = findViewById(R.id.editTextTglLahir);
        etUsername = findViewById(R.id.etUsername);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerReligion = findViewById(R.id.spinnerReligion);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize spinners and other UI components...
        initializeSpinners();

        editTextTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void initializeSpinners() {
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this, R.array.religion_array, android.R.layout.simple_spinner_item);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReligion.setAdapter(religionAdapter);
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String formattedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        editTextTglLahir.setText(formattedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void registerUser() {
        String url = "http://10.10.4.34/Simpan.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Registration failed. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("User", etUsername.getText().toString());
                params.put("Nama", etFullName.getText().toString());
                params.put("Email", etEmail.getText().toString());
                params.put("Password", etPassword.getText().toString());
                params.put("Alamat", etAddress.getText().toString());
                params.put("NoTelp", etPhoneNumber.getText().toString());
                params.put("JenisKelamin", spinnerGender.getSelectedItem().toString());
                params.put("Agama", spinnerReligion.getSelectedItem().toString());
                params.put("TanggalLahir", editTextTglLahir.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
