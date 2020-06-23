package com.example.stirl.greenlight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.example.stirl.greenlight.FragmentHome.context;

public class Login extends AppCompatActivity {

    EditText userName, passWord;
    Button login, register;

    public static final String PREFERENCES = "Prefs";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.txtUname);
        passWord = (EditText) findViewById(R.id.txtPword);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegister);

        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //Toast.makeText(context, prefs.getString("prof_id", null), Toast.LENGTH_SHORT).show();
        String i = sharedPreferences.getString("ru_userid", null);
        //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
        i = i.trim();
        if(!i.equals(""))
        {
            Intent intent = new Intent(Login.this,MainActivity.class);
            //i.putExtra("id",result);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestAccountCredential(getApplicationContext()).execute();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, AccountSetup.class);
                startActivity(i);
            }
        });
    }


    public class TestAccountCredential extends AsyncTask<String,Void,String> {
        Context ctx;

        TestAccountCredential(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            //""
            String login_url = "http://"+ getResources().getString(R.string.ipaddress) +"/getLogin.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(userName.getText().toString().trim(),"UTF-8")
                        +"&"+ URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(passWord.getText().toString().trim(),"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
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
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("login_user");
                    if(feedvalues.length()!=0)
                    for (int i = 0; i < feedvalues.length(); i++) {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String ru_userid = feedarray.getString("ru_userid");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("ru_userid",ru_userid);
                            editor.commit();

                            Intent intent = new Intent(Login.this,MainActivity.class);
                            //i.putExtra("id",result);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                    }
                    else
                        Toast.makeText(ctx, "No account found.", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}
