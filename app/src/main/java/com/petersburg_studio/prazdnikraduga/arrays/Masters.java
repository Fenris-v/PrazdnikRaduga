package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class Masters {
    private int name;
    private int imageResourceId;
    private int content;
    private int url;

    public static final Masters[] masters = {
            new Masters(R.string.m_coffee_title, R.drawable.m_coffee, R.string.m_coffee_content, R.string.m_coffee_url),
            new Masters(R.string.m_eggs_title, R.drawable.m_eggs, R.string.m_eggs_content, R.string.m_eggs_url),
            new Masters(R.string.m_masks_title, R.drawable.m_masks, R.string.m_masks_content, R.string.m_masks_url),
            new Masters(R.string.m_origami_title, R.drawable.m_origami, R.string.m_origami_content, R.string.m_origami_url),
            new Masters(R.string.m_rainbow_title, R.drawable.m_rainbow, R.string.m_rainbow_content, R.string.m_rainbow_url)
    };

    private Masters(int name, int imageResourceId, int content, int url) {
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
