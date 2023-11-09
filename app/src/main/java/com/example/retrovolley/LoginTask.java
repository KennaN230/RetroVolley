// LoginTask.java
package com.example.retrovolley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginTask extends AsyncTask<String, Void, String> {
    private ProgressDialog progressDialog;
    private Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String password = params[7];

        try {
            // Create the request URL
            String baseUrl = "http://10.10.4.34/Koneksi/Login.php";
            String urlParams = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL(baseUrl + "?" + urlParams);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read response
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            // Close connections
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error during login";
        }
    }

    @Override
    protected void onPostExecute(String response) {
        progressDialog.dismiss();

        // Handle the login response
        if (response.equals("Login successful")) {
            // Login successful, start the Test activity
            Intent intent = new Intent(context, Test.class);
            context.startActivity(intent);
        } else {
            // Login failed, show a Toast with the error message
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
        }
    }
}
