package com.example.stirl.greenlight;

import android.content.Context;
import android.content.Intent;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.homeViewHolder> {

    Context mContext;
    List<HomeModel> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, List<HomeModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public homeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_home,parent,false);
        homeViewHolder vHolder = new homeViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(homeViewHolder holder, int position) {

        holder.tv_ID.setText(mData.get(position).getItemHomeID());
        holder.tv_Title.setText(mData.get(position).getItemHomeTitle());
        holder.tv_Desc.setText(mData.get(position).getItemHomeDesc());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class homeViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_ID;
        private TextView tv_Title;
        private TextView tv_Desc;
        private TextView btn_ID;

        public homeViewHolder(View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.txtHomeID);
            tv_Title = (TextView) itemView.findViewById(R.id.txtItemName);
            tv_Desc = (TextView) itemView.findViewById(R.id.txtItemDesc);
            btn_ID = (Button) itemView.findViewById(R.id.btnViewHomeDetails);

            btn_ID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),HomeDetails.class);
                    i.putExtra("id",tv_ID.getText().toString());
                    v.getContext().startActivity(i);
                }
            });
        }
    }

}