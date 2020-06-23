package com.example.stirl.greenlight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
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

import static com.example.stirl.greenlight.FragmentHome.adapter;
import static com.example.stirl.greenlight.FragmentHome.context;

public class FragmentProfile extends Fragment {

    TextView profName;
    TextView profProf;
    TextView currUnits;
    TextView licDate;
    TextView profStatus;
    TextView msg;
    Button logOut;
    ImageView profileimage;
    List<FragmentProfile> mData = new ArrayList<>();
    View v;

    SharedPreferences prefs;

    public String i;

    public FragmentProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.profile_fragment,container,false);
        //Toast.makeText(this.getContext(), getArguments().getString("id"), Toast.LENGTH_SHORT).show();

        //final String i = getArguments().getString("id");

        profName = (TextView) v.findViewById(R.id.txtProfileName);
        profProf = (TextView) v.findViewById(R.id.txtProfileProf);
        currUnits = (TextView) v.findViewById(R.id.txtCurrUnits);
        licDate = (TextView) v.findViewById(R.id.txtLicDate);
        profStatus = (TextView) v.findViewById(R.id.txtProfStatus);
        msg = (TextView) v.findViewById(R.id.txtMsg);
        logOut = (Button) v.findViewById(R.id.btnLogout);
        profileimage = v.findViewById(R.id.profileimage);

        prefs = context.getSharedPreferences("Prefs",context.MODE_PRIVATE);
        //Toast.makeText(context, prefs.getString("prof_id", null), Toast.LENGTH_SHORT).show();
        i = prefs.getString("ru_userid", null);
        //Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
        i = i.trim();
        new FragmentProfile.getProfileDetails(context).execute(i);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences  sharedPreferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ru_userid","");
                editor.commit();

                Toast.makeText(getContext(), "You have logged out your account!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

        new FragmentProfile.getProfileDetails(context).execute(i);
    }

    public class getProfileDetails extends AsyncTask<String,Void,String> {
        Context ctx;

        getProfileDetails(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String login_url = "http://"+getResources().getString(R.string.ipaddress)+"/getProfDetails.php";
            try {
                URL url = new URL(login_url);
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
            if (result != null && !result.isEmpty()) {
                try {
                    feedcontentvalues = new JSONObject(result);

                    try {
                        JSONArray feedvalues = feedcontentvalues.getJSONArray("UserDetails");
                        for (int i = 0; i < feedvalues.length(); i++) {
                            JSONObject feedarray = feedvalues.getJSONObject(i);
                            System.out.println(feedarray);
                            profName.setText(feedarray.getString("ru_fullname"));
                            profProf.setText(feedarray.getString("ru_profession"));
                            currUnits.setText(feedarray.getString("ru_email"));
                            licDate.setText(feedarray.getString("ru_dateadded"));
                            ColorGenerator generator = ColorGenerator.MATERIAL;
                            int color1 = generator.getRandomColor();
                            TextDrawable.IBuilder builder = TextDrawable.builder()
                                    .beginConfig()
                                    .withBorder(4)
                                    .endConfig()
                                    .round();
                            TextDrawable ic1 = builder.build(feedarray.getString("ru_fullname").substring(0,2), color1);
                            profileimage.setImageDrawable(ic1);
                        }
                        if (profStatus.getText().toString().equals("TRUE")) {
                            msg.setText("Congratulations! you have reached the required CPD units for your profession and can now visit the PRC office to renew your license!");
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
