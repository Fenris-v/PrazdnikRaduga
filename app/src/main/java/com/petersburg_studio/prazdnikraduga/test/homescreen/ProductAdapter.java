package com.petersburg_studio.prazdnikraduga.test.homescreen;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
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
import com.petersburg_studio.prazdnikraduga.test.ItemDetailActivity;
import com.petersburg_studio.prazdnikraduga.test.pojo.Product;

public class ProductAdapter extends PagedListAdapter<Product, ProductAdapter.ProductViewHolder> {

    final Product product;

    private static DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
    private Context ctx;

    public ProductAdapter(Context ctx) {
        super(DIFF_CALLBACK);
        this.ctx = ctx;
        product = null;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.all_card_optioned_image, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = getItem(position);
        assert product != null;
        holder.textView.setText(product.getName());

        Glide.with(ctx)
                .load(product.getImg_url())
                .placeholder(R.drawable.cat_wait)
                .into(holder.imageView);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        ProductViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.category_image);
            textView = view.findViewById(R.id.category_text);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(ctx, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailActivity.EXTRA_TEXT, getItem(pos).getName());
                        intent.putExtra(ItemDetailActivity.EXTRA_IMG, getItem(pos).getImg_url());
                        ctx.startActivity(intent);
                    }
                }
            });
        }
    }
}
