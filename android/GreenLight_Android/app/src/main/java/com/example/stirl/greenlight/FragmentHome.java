package com.example.stirl.greenlight;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    public static Context context;
    public static RecyclerViewAdapter adapter;
    public static HomeModel setgetvar;
    Button tutorial_button;

    public  static  RecyclerViewAdapter itemAdapter;

    ImageView workimage;
    View v;
    public static RecyclerView HomeRecyclerView;
    public static List<HomeModel> lstHome = new ArrayList<>();

    public String i;
    SharedPreferences prefs;

    public FragmentHome() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment,container,false);
        context =  getContext();

        tutorial_button = (Button)v.findViewById(R.id.btnTutorial);
        HomeRecyclerView = (RecyclerView) v.findViewById(R.id.rvHome);
        HomeRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        workimage = v.findViewById(R.id.workimage);

        prefs = context.getSharedPreferences("Prefs",context.MODE_PRIVATE);
        //Toast.makeText(context, prefs.getString("prof_id", null), Toast.LENGTH_SHORT).show();
        i = prefs.getString("ru_userid", null);
        //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
        i = i.trim();

        new FragmentHome.getItems(context).execute(i);

        tutorial_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/watch?v=Yeg29d5mxJY"));
                v.getContext().startActivity(viewIntent);
            }
        });
        Picasso.with(getContext())
                .load("http://"+getResources().getString(R.string.ipaddress)+"/opportunity.png")
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(workimage);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        new getItems(context).execute(i);
    }

    public class getItems extends AsyncTask<String,Void,String> {
        Context ctx;

        getItems(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String getHomeItemsURL = "http://"+getResources().getString(R.string.ipaddress)+"/getHome.php";

            try {
                URL url = new URL(getHomeItemsURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("id","UTF-8")+"="+ URLEncoder.encode(params[0],"UTF-8");
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

            JSONObject feedcontentvalues = null;
//            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            if(result != null && !result .isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);
//                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    try {
//                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("get_jobs");
                        lstHome.clear();
                        for (int i = 0; i < feedvalues.length(); i++) {
                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            String id = feedarray.getString("rj_jobid");
                            String seminarTitle = feedarray.getString("rj_jobname");
                            String seminarDesc = feedarray.getString("rj_jobdesc");

                            HomeModel current1 = new HomeModel(id,seminarTitle, seminarDesc);

                            lstHome.add(current1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(context, lstHome.get(0).toString(), Toast.LENGTH_SHORT).show();
                    adapter = new RecyclerViewAdapter(context, lstHome);
                    HomeRecyclerView.setAdapter(null);
                    HomeRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
