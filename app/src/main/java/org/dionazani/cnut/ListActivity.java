package org.dionazani.cnut;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        OkHttpClient client = new OkHttpClient();

        String endpoint = IPSetting.URL + "/contact";
        Request request = new Request.Builder()
                .get()
                .url(endpoint)
                .build();

        final ProgressDialog pd = new ProgressDialog(ListActivity.this);
        pd.setTitle("Loading");
        pd.setCancelable( false );
        pd.show();

        LinearLayoutManager manager = new LinearLayoutManager(ListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            String hasil = response.body().string();
                            JSONArray data = new JSONArray(hasil);

                            ContactAdapter adapter = new ContactAdapter();
                            adapter.contactList = new ArrayList<>();

                            Log.e("Jumlah", String.valueOf(data.length()));

                            for (int i=0; i<data.length(); i++) {
                                ContactModel model = new ContactModel();

                                int id = Integer.parseInt(data.getJSONObject(i).get("id").toString());
                                String nama = String.valueOf(data.getJSONObject(i).get("nama"));
                                String alamat = String.valueOf(data.getJSONObject(i).get("alamat"));
                                String email = String.valueOf(data.getJSONObject(i).get("email"));

                                model.setId(id);
                                model.setNama(nama);
                                model.setAlamat(alamat);
                                model.setEmail(email);

                                adapter.contactList.add(model);
                            }

                            recyclerView.setAdapter(adapter);
                            pd.dismiss();

                        } catch (Exception e) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
