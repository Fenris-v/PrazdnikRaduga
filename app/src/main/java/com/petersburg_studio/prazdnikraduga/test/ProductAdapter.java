package com.petersburg_studio.prazdnikraduga.test;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petersburg_studio.prazdnikraduga.R;

public class ProductAdapter extends PagedListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private Context ctx;

    ProductAdapter(Context ctx) {
        super(DIFF_CALLBACK);
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.all_card_optioned_image, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = getItem(position);

        if (product != null) {
            Glide.with(ctx)
                    .load(product.img_url)
//                    .placeholder(R.drawable.)
                    .into(holder.imageView);

            holder.textView.setText(product.name);
        }
    }

    private static DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
                    return oldProduct.id == newProduct.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
                    return oldProduct.equals(newProduct);
                }
            };

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            textView = itemView.findViewById(R.id.category_text);
        }
    }
}
