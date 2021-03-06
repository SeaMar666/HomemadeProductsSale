package com.example.homemadeproductssale.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Fragments.BasketFragment;
import com.example.homemadeproductssale.R;
import com.example.homemadeproductssale.UpdateActivity;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList product_id,product_price, product_name;

    public OptionsAdapter(Activity activity, Context context, ArrayList product_id,ArrayList product_price, ArrayList product_name){
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_name = product_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.product_price.setText(String.valueOf(product_price.get(position)));
        holder.product_idTxt.setText(String.valueOf(product_id.get(position)));
        holder.product_titleTxt.setText(String.valueOf(product_name.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("name", String.valueOf(product_name.get(position)));
                intent.putExtra("price", String.valueOf(product_price.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_idTxt, product_titleTxt, product_price;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_idTxt = itemView.findViewById(R.id.product_id_txt);
            product_titleTxt = itemView.findViewById(R.id.product_title_txt);
            product_price = itemView.findViewById(R.id.product_price_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }

    }

}
