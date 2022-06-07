package com.abdallah.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Member> mlist = new ArrayList<>();
    private Context context;
    public Adapter(Context context, ArrayList<Member> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member member = mlist.get(position);
        holder.tv_email.setText(member.getEmail());
        holder.tv_name.setText(member.getName());
        holder.tv_phone.setText(member.getPhone());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setAllMsgTypes(ArrayList<Member> mlist) {
        this.mlist = mlist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_phone,tv_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_email = itemView.findViewById(R.id.tv_email);
        }


    }
}
