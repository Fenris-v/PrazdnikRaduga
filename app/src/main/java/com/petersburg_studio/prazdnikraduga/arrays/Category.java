package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class Category {
    private int name;
    private int imageResourceId;

    public static final Category[] categories = {
            new Category(R.string.animators, R.drawable.animators_category),
            new Category(R.string.masters, R.drawable.master_klass_category),
            new Category(R.string.shows, R.drawable.show_category),
            new Category(R.string.additional, R.drawable.additional),
            new Category(R.string.thematic_party, R.drawable.thematic_category),
            new Category(R.string.seasons, R.drawable.seasons_category)
    };

    private Category(int name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public int getName(){
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
