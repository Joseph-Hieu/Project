package com.example.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ItemLongClickListener;
import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.object.User;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<User> mDataset;
    private ItemLongClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView mssv;
        public TextView name;
        public TextView gioitinh;
        public TextView sdt;
        public TextView email;
        public TextView phongban;
        public View root;

        public MyViewHolder(View v) {
            super(v);
            root = v.findViewById(R.id.root);
            mssv = v.findViewById(R.id.mssv);
            name = v.findViewById(R.id.name);
            gioitinh = v.findViewById(R.id.gioitinh);
            sdt = v.findViewById(R.id.soDT);
            email = v.findViewById(R.id.email);
            phongban = v.findViewById(R.id.phongban);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<User> myDataset, ItemLongClickListener _listener) {
        mDataset = myDataset;
        this.listener = _listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mssv.setText(mDataset.get(position).getMaNV());
        holder.name.setText(mDataset.get(position).getTenNV());
        holder.gioitinh.setText(mDataset.get(position).getGioiTinh());
        holder.sdt.setText(mDataset.get(position).getSoDT());
        holder.email.setText(mDataset.get(position).getEmail());
        holder.phongban.setText(mDataset.get(position).getsPhongBan());

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.longClickListener(mDataset.get(position).getMaNV());
                return true;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}