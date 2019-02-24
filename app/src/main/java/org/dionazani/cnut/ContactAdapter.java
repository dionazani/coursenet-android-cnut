package org.dionazani.cnut;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private ArrayList<ContactModel> contactList;

    public ContactAdapter(ArrayList<ContactModel> list) {
        this.contactList = list;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.contact_layout, viewGroup, false);
        return new ContactAdapter.ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.id.setText(Integer.toString(contactList.get(i).getId()));
        contactViewHolder.nama.setText(contactList.get(i).getNama());
        contactViewHolder.alamat.setText(contactList.get(i).getAlamat());
        contactViewHolder.email.setText(contactList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return (contactList != null) ? contactList.size() : 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView id, nama, alamat, email;

        public ContactViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            nama = (TextView) itemView.findViewById(R.id.nama);
            alamat = (TextView) itemView.findViewById(R.id.alamat);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }
}
