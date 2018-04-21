package com.slack.weeklychallengeone.Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slack.weeklychallengeone.R;

import java.util.ArrayList;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Model> mArrayList;
    Context context;

    public MyAdapter(ArrayList<Model> mArrayList, Context context) {
        this.mArrayList = mArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tv_name.setText(mArrayList.get(position).getName());
        holder.tv_info.setText(mArrayList.get(position).getInfo());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.card_view.setBackgroundColor(generateRandomColor());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv_info.setVisibility((holder.tv_info.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
//                holder.card_view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_info;
        private CardView card_view;

        MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_info = view.findViewById(R.id.tv_info);
            card_view = view.findViewById(R.id.card_view_row);
        }
    }

    public int generateRandomColor() {
        // This is the base color which will be mixed with the generated one
        Random mRandom = new Random(System.currentTimeMillis());
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(270)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(260)) / 2;

        return Color.rgb(red, green, blue);
    }
}
