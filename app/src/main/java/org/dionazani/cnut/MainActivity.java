package org.dionazani.cnut;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {

    int jurusanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doBrowse(View view) {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(i);
    }

    public void doAddNew(View view) {
        EditText kodeEt =(EditText) findViewById(R.id.kode_text);
        EditText namaEt = (EditText) findViewById(R.id.nama_text);

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("codeMahasiswa", kodeEt.getText().toString())
                .addFormDataPart("namaMahasiswa", namaEt.getText().toString())
                .build();

        String endpoint = "http://192.168.1.8:8081/coursenet-android-cnut-restapi/api/mahasiswa";
        Request request = new Request.Builder()
                .post(requestBody)
                .url(endpoint)
                .build();

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("Processing ... ");
        pd.setMessage("Please Wait .. ");
        pd.setCancelable(false);

        pd.show();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String eMessage = e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), eMessage, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String hasil = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
