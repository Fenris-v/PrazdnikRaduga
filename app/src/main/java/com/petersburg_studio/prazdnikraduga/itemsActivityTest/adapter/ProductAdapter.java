package com.petersburg_studio.prazdnikraduga.itemsActivityTest.adapter;

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
import com.petersburg_studio.prazdnikraduga.itemsActivityTest.pojo.Product;
import com.petersburg_studio.prazdnikraduga.sliderActivityTest.SliderActivity;

import java.util.Objects;

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
                .load(product.getImg_url0())
                .placeholder(R.drawable.cat_wait)
                .into(holder.imageView);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        View parent;

        ProductViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.category_image);
            textView = view.findViewById(R.id.category_text);
            parent = view.findViewById(R.id.parent);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(ctx, SliderActivity.class);
                        intent.putExtra(SliderActivity.EXTRA_TEXT, Objects.requireNonNull(getItem(pos)).getName());
                        intent.putExtra(SliderActivity.EXTRA_IMG, Objects.requireNonNull(getItem(pos)).getImg_url0());
                        intent.putExtra(SliderActivity.EXTRA_IMG1, Objects.requireNonNull(getItem(pos)).getImg_url1());
                        intent.putExtra(SliderActivity.EXTRA_IMG2, Objects.requireNonNull(getItem(pos)).getImg_url2());
                        intent.putExtra(SliderActivity.EXTRA_IMG3, Objects.requireNonNull(getItem(pos)).getImg_url3());
                        intent.putExtra(SliderActivity.EXTRA_IMG4, Objects.requireNonNull(getItem(pos)).getImg_url4());
                        intent.putExtra(SliderActivity.EXTRA_IMG5, Objects.requireNonNull(getItem(pos)).getImg_url5());
                        intent.putExtra(SliderActivity.EXTRA_IMG6, Objects.requireNonNull(getItem(pos)).getImg_url6());
                        intent.putExtra(SliderActivity.EXTRA_IMG7, Objects.requireNonNull(getItem(pos)).getImg_url7());
                        intent.putExtra(SliderActivity.EXTRA_IMG8, Objects.requireNonNull(getItem(pos)).getImg_url8());
                        intent.putExtra(SliderActivity.EXTRA_IMG9, Objects.requireNonNull(getItem(pos)).getImg_url9());
                        intent.putExtra(SliderActivity.EXTRA_IMG10, Objects.requireNonNull(getItem(pos)).getImg_url10());
                        intent.putExtra(SliderActivity.EXTRA_IMG11, Objects.requireNonNull(getItem(pos)).getImg_url11());
                        intent.putExtra(SliderActivity.EXTRA_IMG12, Objects.requireNonNull(getItem(pos)).getImg_url12());
                        intent.putExtra(SliderActivity.EXTRA_IMG13, Objects.requireNonNull(getItem(pos)).getImg_url13());
                        intent.putExtra(SliderActivity.EXTRA_IMG14, Objects.requireNonNull(getItem(pos)).getImg_url14());
                        ctx.startActivity(intent);
                    }
                }
            });
        }
    }
}
