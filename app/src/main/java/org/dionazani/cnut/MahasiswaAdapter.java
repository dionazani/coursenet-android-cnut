package org.dionazani.cnut;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/*
Source : https://medium.com/easyread/tutorial-android-recyclerview-dan-cardview-9a62aaa6cc0c
 */
public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private ArrayList<MahasiswaModel> mahasiswaList;

    public MahasiswaAdapter(ArrayList<MahasiswaModel> list) {
        this.mahasiswaList = list;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.mahasiswa_row, viewGroup, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder mahasiswaViewHolder, int i) {
        mahasiswaViewHolder.id_mahasiswa.setText(Integer.toString(mahasiswaList.get(i).getId()));
        mahasiswaViewHolder.code_mahasiswa.setText(mahasiswaList.get(i).getKodeMahasiswa());
        mahasiswaViewHolder.nama_mahasiswa.setText(mahasiswaList.get(i).getNamaMahasiswa());
        mahasiswaViewHolder.jurusan.setText(mahasiswaList.get(i).getJurusan());
    }

    @Override
    public int getItemCount() {
        return (mahasiswaList != null) ? mahasiswaList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {

        private TextView id_mahasiswa, code_mahasiswa, nama_mahasiswa, jurusan;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            id_mahasiswa = (TextView) itemView.findViewById(R.id.id_mahasiswa);
            code_mahasiswa = (TextView) itemView.findViewById(R.id.kode_mahasiswa);
            nama_mahasiswa = (TextView) itemView.findViewById(R.id.nama_mahasiswa);
            jurusan = (TextView) itemView.findViewById(R.id.jurusan);
        }
    }
}


