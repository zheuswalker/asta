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
import android.support.v7.widget.SearchView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class FragmentNotif extends Fragment {

    View v;

    public static Context context;
    public static NotifRecyclerViewAdapter adapter;

    public static RecyclerView NotifRecyclerView;
    public static List<NotifModel> lstNotif = new ArrayList<>();

    public String i;
    SharedPreferences prefs;

    public FragmentNotif() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notif_fragment,container,false);
        context = getContext();
        NotifRecyclerView = (RecyclerView) v.findViewById(R.id.rvNotif);
        NotifRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        prefs = context.getSharedPreferences("Prefs",context.MODE_PRIVATE);
        i = prefs.getString("ru_userid", null);
        i = i.trim();

        new FragmentNotif.getNotif(context).execute(i);
        ArrayList<String> list;
        ArrayAdapter<String> adapter;
        SearchView jobSearchView;
        ListView jobList;

//        jobSearchView = (SearchView)v.findViewById(R.id.sv_job);
//        jobList = (ListView)v.findViewById(R.id.lst_job);
//
//        list = new ArrayList<String>();
//        list.add("Academic librarian");
//        list.add("Accountant");
//        list.add("Bid manager");
//        list.add("Business analyst");
//        list.add("Careers adviser (higher education)");
//        list.add("Civil engineer");
//        list.add("Data scientist");
//
//
//        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, list);
//        jobList.setAdapter(adapter);

//        jobSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                adapter.getFilter().filter(s);
//                return false;
//            }
//        });
        
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        new FragmentNotif.getNotif(context).execute(i);
    }

    public class getNotif extends AsyncTask<String,Void,String> {
        Context ctx;

        getNotif(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String getOnlineItemsURL = "http://"+getResources().getString(R.string.ipaddress)+"/getNotif.php";

            try {
                URL url = new URL(getOnlineItemsURL);
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
            //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            if(result != null && !result .isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);

                    try {
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("notifContent");
                        lstNotif.clear();
                        for (int i = 0; i < feedvalues.length(); i++) {
                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            String certTitle = feedarray.getString("cert_title");
                            String certStatus = feedarray.getString("cert_status");
                            String certUnits = feedarray.getString("cert_units");

                            NotifModel current1 = new NotifModel(certTitle, certStatus, certUnits);

                            lstNotif.add(current1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter = new NotifRecyclerViewAdapter(context, lstNotif);
                    NotifRecyclerView.setAdapter(null);
                    NotifRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
