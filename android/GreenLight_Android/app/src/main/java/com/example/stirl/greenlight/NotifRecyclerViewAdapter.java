package com.example.stirl.greenlight;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotifRecyclerViewAdapter extends RecyclerView.Adapter<NotifRecyclerViewAdapter.NotifViewHolder>{

    Context onContext;
    List<NotifModel> onData = new ArrayList<>();

    public NotifRecyclerViewAdapter(Context onContext, List<NotifModel> onData) {
        this.onContext = onContext;
        this.onData = onData;
    }

    @NonNull
    @Override
    public NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(onContext).inflate(R.layout.item_notif,parent,false);
        NotifRecyclerViewAdapter.NotifViewHolder vNotifViewHolder = new NotifRecyclerViewAdapter.NotifViewHolder(v);
        return vNotifViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifRecyclerViewAdapter.NotifViewHolder holder, int position) {
        holder.tv_certTitle.setText(onData.get(position).getCertTitle());
        holder.tv_certStatus.setText(onData.get(position).getCertStatus());
        holder.tv_certUnits.setText(onData.get(position).getCertUnits());
    }

    @Override
    public int getItemCount() {
        return onData.size();
    }

    public static class NotifViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_certTitle;
        private TextView tv_certStatus;
        private TextView tv_certUnits;

        public NotifViewHolder(View itemView) {
            super(itemView);

            tv_certTitle = (TextView) itemView.findViewById(R.id.txtNotifName);
            tv_certStatus = (TextView) itemView.findViewById(R.id.txtNotifStatus);
            tv_certUnits = (TextView) itemView.findViewById(R.id.txtNotifUnits);
        }
    }
}
