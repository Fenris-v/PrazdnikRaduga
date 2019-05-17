package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class Shows {
    private int name;
    private int imageResourceId;
    private int content;
    private int url;

    public static final Shows[] shows = {
            new Shows(R.string.s_bubbles_title, R.drawable.s_puzyri, R.string.s_bubbles_content, R.string.s_bubbles_url),
            new Shows(R.string.s_chemistry_title, R.drawable.s_him, R.string.s_chemistry_content, R.string.s_chemistry_url),
            new Shows(R.string.s_paper_title, R.drawable.s_bumaga, R.string.s_paper_content, R.string.s_paper_url),
            new Shows(R.string.s_tesla_title, R.drawable.s_tesla, R.string.s_tesla_content, R.string.s_tesla_url),
            new Shows(R.string.s_fire_title, R.drawable.s_fire, R.string.s_fire_content, R.string.s_fire_url)
    };

    private Shows(int name, int imageResourceId, int content, int url) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.content = content;
        this.url = url;
    }

    public int getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getContent() {
        return content;
    }

    public int getUrl() {
        return url;
    }
}
