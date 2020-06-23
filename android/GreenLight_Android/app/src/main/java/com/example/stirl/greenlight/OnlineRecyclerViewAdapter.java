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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OnlineRecyclerViewAdapter extends RecyclerView.Adapter<OnlineRecyclerViewAdapter.OnlineViewHolder> {

    Context onContext;
    List<OnlineModel> onData = new ArrayList<>();

    public OnlineRecyclerViewAdapter(Context onContext, List<OnlineModel> onData) {
        this.onContext = onContext;
        this.onData = onData;
    }

    @NonNull
    @Override
    public OnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(onContext).inflate(R.layout.item_online,parent,false);
        OnlineViewHolder vOnlineHolder = new OnlineViewHolder(v);
        int width = parent.getMeasuredWidth();
        vOnlineHolder.setIsRecyclable(false);
        v.setMinimumWidth(width);
        return vOnlineHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull OnlineViewHolder holder, int position) {
//        final Constructor Constructor = Constructors.get(position) ;
        final OnlineModel OnlineModel = onData.get(position);
        holder.tv_onlineID.setText(OnlineModel.getItemOnlineID());
        holder.tv_onlineTitle.setText(OnlineModel.getItemOnlineTitle());
        holder.tv_onlineDesc.setText(OnlineModel.getItemOnlineDesc());
        holder.tv_link.setText(OnlineModel.getItemLink());
//        holder.jobname.setText(Constructor.jobname());
//        holder.tv_onlineID.setText(onData.get(position).getItemOnlineID());
//        holder.tv_onlineTitle.setText(onData.get(position).getItemOnlineTitle());
//        holder.tv_onlineDesc.setText(onData.get(position).getItemOnlineDesc());
//        holder.tv_link.setText(onData.get(position).getItemLink());
    }

    @Override
    public int getItemCount() {
        return onData.size();
    }

    public static class OnlineViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_onlineID;
        private TextView tv_onlineTitle;
        private TextView tv_onlineDesc;
        private TextView tv_link;
        private Button btn_ViewDetails;

        public OnlineViewHolder(View itemView) {
            super(itemView);

            tv_onlineID = (TextView) itemView.findViewById(R.id.txtOnlineID);
            tv_onlineTitle = (TextView) itemView.findViewById(R.id.txtItemName);
            tv_onlineDesc = (TextView) itemView.findViewById(R.id.txtItemDesc);
            tv_link = (TextView) itemView.findViewById(R.id.txtOnlineLink);
            btn_ViewDetails = (Button) itemView.findViewById(R.id.btnViewOnlineDetails);

            btn_ViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),HomeDetails.class);
                    i.putExtra("id",tv_onlineID.getText().toString());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
