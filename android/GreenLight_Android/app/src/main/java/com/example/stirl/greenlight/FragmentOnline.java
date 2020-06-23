package com.example.stirl.greenlight;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.SearchView;
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
import java.util.ArrayList;
import java.util.List;

public class FragmentOnline extends Fragment {

    Button btn_Extract;
    View v;
    SearchView searchView;

    public static Context context;
    public static OnlineRecyclerViewAdapter adapter;

    public static RecyclerView OnlineRecyclerView;
    public static List<OnlineModel> lstOnline = new ArrayList<>();

    public String i;
    SharedPreferences prefs;

    public FragmentOnline() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.online_fragment,container,false);
        context = getContext();
//        Search View
        searchView = (SearchView)v.findViewById(R.id.sv_job);

        OnlineRecyclerView = (RecyclerView) v.findViewById(R.id.rvOnline);
        OnlineRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        prefs = context.getSharedPreferences("Prefs",context.MODE_PRIVATE);
        i = prefs.getString("ru_userid", null);
        i = i.trim();

        new getOnlineItems(getActivity()).execute("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("Search Text", s);
                new getOnlineItems(getActivity()).execute(s);
                return false;
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        new getOnlineItems(context).execute(i);
    }

    public class getOnlineItems extends AsyncTask<String,Void,String> {
        Context ctx;

        getOnlineItems(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String getOnlineItemsURL = "http://"+getResources().getString(R.string.ipaddress)+"/getOnlineItems.php";
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
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("onlineContentswhere");
                        lstOnline.clear();
                        for (int i = 0; i < feedvalues.length(); i++) {

                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            String id = feedarray.getString("rj_jobid");
                            String seminarTitle = feedarray.getString("rj_jobname");
                            String seminarDesc = feedarray.getString("rj_jobdesc");
                            String seminarLink = feedarray.getString("rj_jobroles");

                            OnlineModel current1 = new OnlineModel(id,seminarTitle, seminarDesc, seminarLink);
//                            Toast.makeText(context, seminarTitle, Toast.LENGTH_SHORT).show();
                            lstOnline.add(current1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(context, lstOnline.get(0).toString(), Toast.LENGTH_SHORT).show();
                    adapter = new OnlineRecyclerViewAdapter(context, lstOnline);
                    OnlineRecyclerView.setAdapter(null);
                    OnlineRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
    }

}
