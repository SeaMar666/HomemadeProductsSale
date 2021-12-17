package com.example.homemadeproductssale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyHolder> {

    private Context mContext;
    private List<Product> mData;

    public ProductsAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ProductsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_product,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.price.setText(String.valueOf(mData.get(position).getPrice()));
        holder.productImage.setImageResource(mData.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView name,price;
        ImageView productImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.productName_text);
            price = itemView.findViewById(R.id.price_text);
            productImage = itemView.findViewById(R.id.product_image);

        }
    }
}
