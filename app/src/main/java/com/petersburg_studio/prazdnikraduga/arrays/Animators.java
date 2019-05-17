package com.petersburg_studio.prazdnikraduga.arrays;

import com.petersburg_studio.prazdnikraduga.R;

public class Animators {
    private int name;
    private int imageResourceId;
    private int type;
    private int content;
    private int number;
    private int url;

//    cartoon 1
//    fairy 2
//    game 3
//    real 4
//    superhero 5
    public static final Animators[] animators = {
            new Animators(R.string.tmnt_title, R.drawable.a_tmnt, 1, R.string.tmnt_content, 1, R.string.tmnt_url),
            new Animators(R.string.captain_title, R.drawable.a_capitan, 2, R.string.captain_content, 2, R.string.captain_url),
            new Animators(R.string.angry_birds_title, R.drawable.a_angry_birds, 3, R.string.angry_birds_content, 3, R.string.angry_birds_url),
            new Animators(R.string.pirates_title, R.drawable.a_pirates, 4, R.string.pirates_content, 4, R.string.pirates_url),
            new Animators(R.string.batman_title, R.drawable.a_batman, 5, R.string.batman_content, 5, R.string.batman_url)
    };

    private Animators(int name, int imageResourceId, int type, int content, int number, int url) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.type = type;
        this.content = content;
        this.number = number;
        this.url = url;
    }

    public int getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getType() {
        return type;
    }

    public int getContent() {
        return content;
    }

    public int getNumber() {
        return number;
    }

    public int getUrl() {
        return url;
    }
}
