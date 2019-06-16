package com.petersburg_studio.prazdnikraduga.adapters.othersActivity;

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
import com.petersburg_studio.prazdnikraduga.AnimatorDetailActivity;
import com.petersburg_studio.prazdnikraduga.OtherDetailActivity;
import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

import java.util.Objects;

public class OthersAdapter extends PagedListAdapter<Items, OthersAdapter.ProductViewHolder> {

    private static DiffUtil.ItemCallback<Items> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Items>() {
                @Override
                public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
    private Context ctx;

    public OthersAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_card_optioned_image, parent, false);
        ctx = parent.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Items items = getItem(position);
        assert items != null;
        holder.textView.setText(items.getName());

        Glide.with(ctx)
                .load(items.getImg_url())
                .placeholder(R.drawable.cat_wait)
                .centerCrop()
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

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(parent.getContext(), OtherDetailActivity.class);
                    intent.putExtra(AnimatorDetailActivity.EXTRA_NAME, Objects.requireNonNull(getItem(pos)).getName());
                    intent.putExtra(AnimatorDetailActivity.EXTRA_CONTENT, Objects.requireNonNull(getItem(pos)).getContent0());
//                        intent.putExtra(AnimatorDetailActivity.EXTRA_CONTENT1, Objects.requireNonNull(getItem(pos)).getContent1());
                    intent.putExtra(AnimatorDetailActivity.EXTRA_URL, Objects.requireNonNull(getItem(pos)).getItem_url());
                    intent.putExtra(AnimatorDetailActivity.EXTRA_IMG, Objects.requireNonNull(getItem(pos)).getImg_url());
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
