package com.zkhy.fenggang.community.view.photos.ratingView;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.example.xlhratingbar_lib.IRatingView;
import com.zkhy.fenggang.community.R;

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/9 on 10:33
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class SimpleRatingView implements IRatingView {

    @Override
    public int getStateRes(int posi, int state) {
        int id = R.drawable.ic_star_empty;
        switch (state) {
            case STATE_NONE:
                id = R.drawable.ic_star_empty;
                break;
            case STATE_HALF:
                id = R.drawable.ic_star_empty;
                break;
            case STATE_FULL:
                id = R.drawable.ic_star_fill;
                break;
            default:
                break;
        }
        return id;
    }

    @Override
    public int getCurrentState(float rating, int numStars, int position) {
        position++;
        float dis = position - rating;
        if (dis <= 0) {
            return STATE_FULL;
        }
        if (dis == 0.5) {
            return STATE_HALF;
        }
        if (dis > 0.5) {
            return STATE_NONE;
        }
        return 0;
    }


    @Override
    public ImageView getRatingView(Context context, int numStars, int posi) {
//        ImageView imageView = new ImageView(context);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(56, 36));

        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.layout_star_view_4_photo, null);


        return imageView;
    }
}
