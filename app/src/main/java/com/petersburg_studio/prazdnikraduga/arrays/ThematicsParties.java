package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class ThematicsParties {
    private int name;
    private int imageResourceId;
    private int content;
    private int url;

    public static final ThematicsParties[] thematicsParties = {
            new ThematicsParties(R.string.t_chocolate_title, R.drawable.t_chocolate, R.string.t_chocolate_content, R.string.t_chocolate_url),
            new ThematicsParties(R.string.t_mexico_title, R.drawable.t_mexico, R.string.t_mexico_content, R.string.t_mexico_url),
            new ThematicsParties(R.string.t_pirates_title, R.drawable.t_pirates, R.string.t_pirates_content, R.string.t_pirates_url),
            new ThematicsParties(R.string.t_science_title, R.drawable.t_science, R.string.t_science_content, R.string.t_science_url),
            new ThematicsParties(R.string.t_theater_title, R.drawable.t_theater, R.string.t_theater_content, R.string.t_theater_url)
    };

    private ThematicsParties(int name, int imageResourceId, int content, int url) {
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
