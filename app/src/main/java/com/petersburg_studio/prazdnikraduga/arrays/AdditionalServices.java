package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class AdditionalServices {
    private int name;
    private int imageResourceId;
    private int content;
    private int url;

    public static final AdditionalServices[] additionalServices = {
            new AdditionalServices(R.string.add_bubbles_title, R.drawable.add_bubbles, R.string.add_bubbles_content, R.string.add_bubbles_url),
            new AdditionalServices(R.string.add_dj_title, R.drawable.add_dj, R.string.add_dj_content, R.string.add_dj_url),
            new AdditionalServices(R.string.add_dolls_theater_title, R.drawable.add_dolls_theater, R.string.add_dolls_theater_content, R.string.add_dolls_theater_url),
            new AdditionalServices(R.string.add_light_title, R.drawable.add_light, R.string.add_light_content, R.string.add_light_url),
            new AdditionalServices(R.string.add_music_title, R.drawable.add_music, R.string.add_music_content, R.string.add_music_url)
    };

    private AdditionalServices(int name, int imageResourceId, int content, int url) {
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
