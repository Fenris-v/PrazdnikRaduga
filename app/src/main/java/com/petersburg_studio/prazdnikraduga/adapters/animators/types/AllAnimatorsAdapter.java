package com.petersburg_studio.prazdnikraduga.adapters.animators.types;

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
import com.petersburg_studio.prazdnikraduga.R;
import com.petersburg_studio.prazdnikraduga.pojo.Items;

import java.util.Objects;

public class AllAnimatorsAdapter extends PagedListAdapter<Items, AllAnimatorsAdapter.ProductViewHolder> {

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

    public AllAnimatorsAdapter() {
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
                .load(items.getImg_url0())
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
                        Intent intent = new Intent(parent.getContext(), AnimatorDetailActivity.class);
                        intent.putExtra(AnimatorDetailActivity.EXTRA_NAME, Objects.requireNonNull(getItem(pos)).getName());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_CONTENT, Objects.requireNonNull(getItem(pos)).getContent0());
//                        intent.putExtra(AnimatorDetailActivity.EXTRA_CONTENT1, Objects.requireNonNull(getItem(pos)).getContent1());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_URL, Objects.requireNonNull(getItem(pos)).getItem_url());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG, Objects.requireNonNull(getItem(pos)).getImg_url0());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG1, Objects.requireNonNull(getItem(pos)).getImg_url1());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG2, Objects.requireNonNull(getItem(pos)).getImg_url2());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG3, Objects.requireNonNull(getItem(pos)).getImg_url3());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG4, Objects.requireNonNull(getItem(pos)).getImg_url4());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG5, Objects.requireNonNull(getItem(pos)).getImg_url5());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG6, Objects.requireNonNull(getItem(pos)).getImg_url6());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG7, Objects.requireNonNull(getItem(pos)).getImg_url7());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG8, Objects.requireNonNull(getItem(pos)).getImg_url8());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG9, Objects.requireNonNull(getItem(pos)).getImg_url9());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG10, Objects.requireNonNull(getItem(pos)).getImg_url10());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG11, Objects.requireNonNull(getItem(pos)).getImg_url11());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG12, Objects.requireNonNull(getItem(pos)).getImg_url12());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG13, Objects.requireNonNull(getItem(pos)).getImg_url13());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG14, Objects.requireNonNull(getItem(pos)).getImg_url14());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG15, Objects.requireNonNull(getItem(pos)).getImg_url15());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG16, Objects.requireNonNull(getItem(pos)).getImg_url16());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG17, Objects.requireNonNull(getItem(pos)).getImg_url17());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG18, Objects.requireNonNull(getItem(pos)).getImg_url18());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG19, Objects.requireNonNull(getItem(pos)).getImg_url19());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG20, Objects.requireNonNull(getItem(pos)).getImg_url20());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG21, Objects.requireNonNull(getItem(pos)).getImg_url21());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG22, Objects.requireNonNull(getItem(pos)).getImg_url22());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG23, Objects.requireNonNull(getItem(pos)).getImg_url23());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG24, Objects.requireNonNull(getItem(pos)).getImg_url24());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG25, Objects.requireNonNull(getItem(pos)).getImg_url25());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG26, Objects.requireNonNull(getItem(pos)).getImg_url26());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG27, Objects.requireNonNull(getItem(pos)).getImg_url27());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG28, Objects.requireNonNull(getItem(pos)).getImg_url28());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG29, Objects.requireNonNull(getItem(pos)).getImg_url29());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG30, Objects.requireNonNull(getItem(pos)).getImg_url30());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG31, Objects.requireNonNull(getItem(pos)).getImg_url31());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG32, Objects.requireNonNull(getItem(pos)).getImg_url32());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG33, Objects.requireNonNull(getItem(pos)).getImg_url33());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG34, Objects.requireNonNull(getItem(pos)).getImg_url34());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG35, Objects.requireNonNull(getItem(pos)).getImg_url35());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG36, Objects.requireNonNull(getItem(pos)).getImg_url36());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG37, Objects.requireNonNull(getItem(pos)).getImg_url37());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG38, Objects.requireNonNull(getItem(pos)).getImg_url38());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG39, Objects.requireNonNull(getItem(pos)).getImg_url39());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG40, Objects.requireNonNull(getItem(pos)).getImg_url40());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG41, Objects.requireNonNull(getItem(pos)).getImg_url41());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG42, Objects.requireNonNull(getItem(pos)).getImg_url42());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG43, Objects.requireNonNull(getItem(pos)).getImg_url43());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG44, Objects.requireNonNull(getItem(pos)).getImg_url44());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG45, Objects.requireNonNull(getItem(pos)).getImg_url45());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG46, Objects.requireNonNull(getItem(pos)).getImg_url46());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG47, Objects.requireNonNull(getItem(pos)).getImg_url47());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG48, Objects.requireNonNull(getItem(pos)).getImg_url48());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG49, Objects.requireNonNull(getItem(pos)).getImg_url49());
                        intent.putExtra(AnimatorDetailActivity.EXTRA_IMG50, Objects.requireNonNull(getItem(pos)).getImg_url50());
                        ctx.startActivity(intent);
                    }
                }
            });
        }
    }
}

