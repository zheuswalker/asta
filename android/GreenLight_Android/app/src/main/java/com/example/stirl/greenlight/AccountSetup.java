package com.example.stirl.greenlight;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class AccountSetup extends AppCompatActivity {

    Button submit;
    EditText userName, passWord, profName, CBprofession;
    Calendar myCalendar;
    List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        userName = (EditText) findViewById(R.id.txtRegUsername);
        passWord = (EditText) findViewById(R.id.txtRegPassword);
        profName = (EditText) findViewById(R.id.txtRegFName);
        submit = (Button) findViewById(R.id.btnSubmit);
        CBprofession = findViewById(R.id.ddProfession);

        //submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Register(getApplicationContext()).execute(profName.getText().toString(), userName.getText().toString(),
                        passWord.getText().toString(), CBprofession.getText().toString());
            }
        });


    }


    public class Register extends AsyncTask<String, Void, String> {
        Context ctx;

        Register(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String fullname = params[0];
            String email = params[1];
            String password = params[2];
            String profession = params[3];

            String login_url = "http://" + getResources().getString(R.string.ipaddress) + "/getRegister.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8")
                        + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                        + "&" + URLEncoder.encode("userpassword", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
                        + "&" + URLEncoder.encode("profession", "UTF-8") + "=" + URLEncoder.encode(profession, "UTF-8")
                        + "&" + URLEncoder.encode("usertype", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("fromweb",result);
            JSONObject feedcontentvalues = null;
            if (result != null && !result.isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("register_user");
                    for (int i = 0; i < feedvalues.length(); i++) {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String vresult = feedarray.getString("result");
                        if (vresult.equals("1")) {
                            Toast.makeText(ctx, "Registered Successfully!, You can now login to your account", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AccountSetup.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ctx, "Registration Failed. Email already used.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
