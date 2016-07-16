package com.zju.app.tutormall.tools;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zju.app.tutormall.R;

import java.util.Random;

public class RandomIcon {
    private static int[] gallary = {
            R.drawable.bg001,
            R.drawable.bg002,
            R.drawable.bg003,
            R.drawable.bg004,
            R.drawable.bg005,
            R.drawable.bg006,
            R.drawable.bg007,
            R.drawable.bg008,
            R.drawable.bg009,
            R.drawable.bg010
    };
    public static void setRandomIcon(Context context, ImageView view) {
        Random random = new Random();
        int index = random.nextInt(10);
        Glide.with(context)
                .load(gallary[index])
                .into(view);
    }

    public static void setRandomIcon(Fragment fragment, ImageView view) {
        setRandomIcon(fragment.getContext(), view);
    }
}
