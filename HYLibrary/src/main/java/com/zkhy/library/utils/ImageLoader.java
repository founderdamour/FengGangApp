package com.zkhy.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.sinothk.view.image.rounded.RoundedImageView;
import com.sinothk.view.image.rounded.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.zkhy.library.R;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/7 on 13:39
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class ImageLoader {
    public static void show(Context mContext, String url, ImageView imageView) {
        try {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(mContext).load(url).placeholder(R.color.my_white).into(imageView);
            } else {
                imageView.setImageResource(R.color.my_white);
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(R.color.my_white);
        }
    }

    public static void show(Context mContext, String url, int defaultImg, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(mContext).load(url).placeholder(defaultImg).into(imageView);
        } else {
            imageView.setImageResource(defaultImg);
        }
    }

    public static void show(Context mContext, String url, int defaultImg, RoundedImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
//            imageView.setOval(true);
//            Glide.with(mContext).load(url).placeholder(defaultImg).into(imageView);


//            Transformation transformation = new RoundedTransformationBuilder()
//                    .borderColor(Color.BLACK)
//                    .borderWidthDp(3)
//                    .cornerRadiusDp(30)
//                    .oval(false)
//                    .build();

            Picasso.with(mContext)
                    .load(url)
                    .centerCrop()
                    .placeholder(defaultImg)
                    .error(defaultImg)
                    .resize(200, 200)
                    .into(imageView);


        } else {
            imageView.setImageResource(defaultImg);
        }
    }
}
