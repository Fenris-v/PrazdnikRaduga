package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class SeasonsHolidays {
    private int name;
    private int imageResourceId;
    private int content;
    private int url;

    public static final SeasonsHolidays[] seasonsHolidays = {
            new SeasonsHolidays(R.string.season_8_title, R.drawable.season_8, R.string.season_8_content, R.string.season_8_url),
            new SeasonsHolidays(R.string.season_9_title, R.drawable.season_9, R.string.season_9_content, R.string.season_9_url),
            new SeasonsHolidays(R.string.season_23_title, R.drawable.season_23, R.string.season_23_content, R.string.season_23_url),
            new SeasonsHolidays(R.string.season_halloween_title, R.drawable.season_halloween, R.string.season_halloween_content, R.string.season_halloween_url),
            new SeasonsHolidays(R.string.season_new_title, R.drawable.season_new, R.string.season_new_content, R.string.season_new_url)
    };

    private SeasonsHolidays(int name, int imageResourceId, int content, int url) {
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
