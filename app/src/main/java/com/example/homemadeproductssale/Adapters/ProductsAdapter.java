package com.example.homemadeproductssale.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Model.Product;
import com.example.homemadeproductssale.ProductPageActivity;
import com.example.homemadeproductssale.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyHolder> {

    private Context mContext;
    private List<Product> mData;
    private OnProductListener mOnProductListener;

    int position;
    public ProductsAdapter(Context mContext, List<Product> mData, OnProductListener onProductListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnProductListener = onProductListener;
    }

    @NonNull
    @Override
    public ProductsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_product, parent, false);
        return new MyHolder(view, mOnProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {

        this.position = position;
        holder.name.setText(mData.get(position).getName());
        holder.price.setText(String.valueOf(mData.get(position).getPrice()));
        holder.productImage.setImageResource(mData.get(position).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductPageActivity.class);
                intent.putExtra("productName", String.valueOf(mData.get(position).getName()));
                intent.putExtra("productPrice", String.valueOf(mData.get(position).getPrice()));
                intent.putExtra("productImage", String.valueOf(mData.get(position).getImage()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, price;
        ImageView productImage;
        OnProductListener onProductListener;
        CardView cardView;

        public MyHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            name = itemView.findViewById(R.id.productName_text);
            price = itemView.findViewById(R.id.price_text);
            productImage = itemView.findViewById(R.id.product_image);
            cardView = itemView.findViewById(R.id.card_view);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProductListener.onProductClick(getAdapterPosition());
        }
    }

    public void clear() {
        int size = mData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mData.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    public interface OnProductListener {
        void onProductClick(int position);
    }
}
