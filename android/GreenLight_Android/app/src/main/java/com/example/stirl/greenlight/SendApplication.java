package com.example.stirl.greenlight;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

public class SendApplication extends AppCompatActivity {

    TextView jobName;
    ImageView IllusImage;
    TextView IllusFile;
    EditText semTitle;
    Button IllusSelectImage,IllusUpload;
    Spinner IllusSwitch;
    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;
    View v;
    Activity mactive;

    SharedPreferences prefs;
    public String a, fileBase64Content="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_application);
        jobName = findViewById(R.id.jobName);
        IllusImage = findViewById(R.id.IllusImage);
        mactive = SendApplication.this;
        jobName.setText(getIntent().getStringExtra("jobName"));
        Picasso.with(getApplicationContext())
                .load("http://"+getResources().getString(R.string.ipaddress)+"/resume")
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(IllusImage);
        IllusFile = findViewById(R.id.IllusFile);
        IllusSelectImage = findViewById(R.id.IllusSelectImage);
        IllusUpload = findViewById(R.id.IllusUpload);
        semTitle = findViewById(R.id.txtUploadSemTitle);

        prefs = getSharedPreferences("Prefs",MODE_PRIVATE);
        //Toast.makeText(context, prefs.getString("prof_id", null), Toast.LENGTH_SHORT).show();
        a = prefs.getString("ru_userid", null);

        a = a.trim();

        IllusSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},CODE_GALLERY_REQUEST);
            }
        });

        IllusUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SubmitCert(getApplicationContext()).execute(getIntent().getStringExtra("jobId"),a,semTitle.getText().toString(),IllusFile.getText().toString());
                IllusFile.setText("");
                semTitle.setText("");
            }
        });

    }
    public class SubmitCert extends AsyncTask<String,Void,String> {
        Context ctx;

        SubmitCert(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            //""
            String login_url = "http://"+getResources().getString(R.string.ipaddress)+"/submitCert.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("jobid","UTF-8")+"="+ URLEncoder.encode(params[0],"UTF-8")
                        +"&"+ URLEncoder.encode("applicant","UTF-8")+"="+ URLEncoder.encode(params[1],"UTF-8")
                        +"&"+ URLEncoder.encode("sidenote","UTF-8")+"="+ URLEncoder.encode(params[2],"UTF-8")
                        +"&"+ URLEncoder.encode("cert","UTF-8")+"="+ URLEncoder.encode(fileBase64Content,"UTF-8");
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
            Log.d("server_response",result);
            if(!result.equals("false"))
            {
                Toast.makeText(ctx, "Application Submitted.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                //i.putExtra("id",result);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
            else
            {
                Toast.makeText(ctx, "Can't process right now.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CODE_GALLERY_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/*");
                startActivityForResult(Intent.createChooser(intent,"Select PDF"),CODE_GALLERY_REQUEST);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You don't have permission to access the Gallery.",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filepath = data.getData();
            File imageFile = new File(getRealPathFromUri(mactive,filepath));
            Log.e("fileur", filepath.getPath());
            fileBase64Content = filetoBase64(imageFile);
            IllusFile.setText(imageFile.getName());
        }
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static String getRealPathFromUri(Activity activity, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private String filetoBase64(File file) {
        String encodedString = null;

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            // TODO: handle exception
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        encodedString = Base64.encodeBytes(bytes);
        Log.i("Strng", encodedString);

        return encodedString;
    }


}
