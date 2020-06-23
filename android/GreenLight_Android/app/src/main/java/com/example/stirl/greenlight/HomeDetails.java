package com.example.stirl.greenlight;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

public class HomeDetails extends AppCompatActivity {

    TextView HDTitle, HDProf, HDUnits, HDFee, HDDate, HDPlace, HDDesc;
    public static String id;
    ImageView workimage;
    Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        final Intent i = getIntent();
        id = i.getStringExtra("id");
        workimage = findViewById(R.id.workimage);

        HDTitle = (TextView) findViewById(R.id.txtHDTitle);
        HDProf = (TextView) findViewById(R.id.txtHDProfession);
        HDUnits = (TextView) findViewById(R.id.txtHDUnits);
        HDFee = (TextView) findViewById(R.id.txtHDFee);
        HDDate = (TextView) findViewById(R.id.txtHDDate);
        HDPlace = (TextView) findViewById(R.id.txtHDPlace);
        HDDesc = (TextView) findViewById(R.id.txtHDDesc);
        apply = findViewById(R.id.apply);
        Picasso.with(getApplicationContext())
                .load("http://"+getResources().getString(R.string.ipaddress)+"/work.jpg")
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(workimage);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SendApplication.class)
                        .putExtra("jobName",HDTitle.getText().toString().trim())
                        .putExtra("jobId",i.getStringExtra("id"))
                );
            }
        });
        if(getIntent().hasExtra("fromJobApplications"))
            apply.setVisibility(View.GONE);
        new HomeDetails.getProfileDetails(getApplicationContext()).execute();

    }

    public class getProfileDetails extends AsyncTask<String,Void,String> {
        Context ctx;

        getProfileDetails(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String login_url = "http://"+getResources().getString(R.string.ipaddress)+"/getHomeDetails.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(id,"UTF-8");
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
            Log.d("fromweb",result);
            JSONObject feedcontentvalues = null;
            //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            if(result != null && !result .isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);

                    try {
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("homeDetailsContent");
                        for (int i = 0; i < feedvalues.length(); i++) {
                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            System.out.println(feedarray);
                            HDTitle.setText(feedarray.getString("rj_jobname"));
                            HDProf.setText(feedarray.getString("rj_jobdesc"));
                            HDUnits.setText(feedarray.getString("rj_jobroles"));
                            HDFee.setText(feedarray.getString("rj_salary"));
                            HDDate.setText("Starting Date: "+feedarray.getString("rj_startingdate"));
                            HDPlace.setText(feedarray.getString("rj_requirements"));
                            HDDesc.setText(feedarray.getString("rj_jobdesc"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

