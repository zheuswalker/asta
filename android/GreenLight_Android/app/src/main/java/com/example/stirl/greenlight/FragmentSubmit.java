package com.example.stirl.greenlight;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.stirl.greenlight.FragmentHome.context;

public class FragmentSubmit extends Fragment {


    Button btn_Extract;
    View v;
    SearchView searchView;

    public static Context context;
    public static JobApllicationsViewAdapter adapter;

    public static RecyclerView OnlineRecyclerView;
    public static List<JobApplicationModel> lstOnline = new ArrayList<>();

    public String i;
    SharedPreferences prefs;

    public FragmentSubmit() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.submit_fragment,container,false);
        context = getContext();
        OnlineRecyclerView = (RecyclerView) v.findViewById(R.id.rvOnline);
        OnlineRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        prefs = context.getSharedPreferences("Prefs",context.MODE_PRIVATE);
        i = prefs.getString("ru_userid", null);
        i = i.trim();

        new getOnlineItems(context).execute(i);

        return v;
    }

    public class getOnlineItems extends AsyncTask<String,Void,String> {
        Context ctx;

        getOnlineItems(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String getOnlineItemsURL = "http://"+getResources().getString(R.string.ipaddress)+"/getuserJobApplications.php";
            Log.d("Keyword", params[0]);
            try {
                URL url = new URL(getOnlineItemsURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("key","UTF-8")+"="+ URLEncoder.encode(params[0],"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                Log.d("doInBackground", data);

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

            JSONObject feedcontentvalues = null;
//            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//            Log.d("JSON-Object", result);

            if(result != null && !result .isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);
//                    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    try {
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("UserJobApplications");
                        lstOnline.clear();
                        for (int i = 0; i < feedvalues.length(); i++) {

                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            String id = feedarray.getString("rj_jobid");
                            String seminarTitle = feedarray.getString("rj_jobname");
                            String seminarDesc = feedarray.getString("rj_jobdesc");
                            String seminarLink = feedarray.getString("rj_jobroles");
                            String rja_status = feedarray.getString("rja_status");

                            JobApplicationModel current1 = new JobApplicationModel(id,seminarTitle, seminarDesc,
                                    seminarLink,rja_status);
//                            Toast.makeText(context, seminarTitle, Toast.LENGTH_SHORT).show();
                            lstOnline.add(current1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(context, lstOnline.get(0).toString(), Toast.LENGTH_SHORT).show();
                    adapter = new JobApllicationsViewAdapter(context, lstOnline);
                    OnlineRecyclerView.setAdapter(null);
                    OnlineRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }

}
