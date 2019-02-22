package org.dionazani.cnut;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private MahasiswaAdapter adapter;
    private ArrayList<MahasiswaModel> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        this.mahasiswaList = new ArrayList<>();
        getMahasiswa();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new MahasiswaAdapter(mahasiswaList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void addMahasiswaToList(MahasiswaModel model) {
        this.mahasiswaList.add(model);
    }

    private void getMahasiswa() {
        this.mahasiswaList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        String endpoint = "http://192.168.1.8:8081/coursenet-android-cnut-restapi/api/mahasiswa";
        Request request = new Request.Builder()
                .get()
                .url(endpoint)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                try {
                    String hasil = response.body().string();
                    JSONArray data = new JSONArray(hasil);

                    for (int i=0; i<data.length(); i++) {
                        MahasiswaModel model = new MahasiswaModel();

                        int id = Integer.parseInt(data.getJSONObject(i).get("id").toString());
                        String kodeMahasiswa = String.valueOf(data.getJSONObject(i).get("code_mhs"));
                        String namaMahasiswa = String.valueOf(data.getJSONObject(i).get("nama_mhs"));
                        String jurusan = String.valueOf(data.getJSONObject(i).get("nama_jur"));

                        model.setId(id);
                        model.setKodeMahasiswa(kodeMahasiswa);
                        model.setNamaMahasiswa(namaMahasiswa);
                        model.setJurusan(jurusan);

                        addMahasiswaToList(model);
                    }
                } catch (Exception e) {

                }
            }
        });

    }
}
