package com.example.homemadeproductssale.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Model.Product;
import com.example.homemadeproductssale.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyHolder> {

    private Context mContext;
    private List<Product> mData;
    private OnProductListener mOnProductListener;

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
        view = mInflater.inflate(R.layout.cardview_product,parent,false);
        return new MyHolder(view, mOnProductListener);
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

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,price;
        ImageView productImage;
        OnProductListener onProductListener;

        public MyHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            name = itemView.findViewById(R.id.productName_text);
            price = itemView.findViewById(R.id.price_text);
            productImage = itemView.findViewById(R.id.product_image);
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

    public interface OnProductListener{
        void onProductClick(int position);
    }
}
