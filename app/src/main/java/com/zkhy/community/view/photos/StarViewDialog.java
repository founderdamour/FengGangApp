package com.zkhy.community.view.photos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xlhratingbar_lib.XLHRatingBar;
import com.zkhy.community.R;
import com.zkhy.community.view.photos.ratingView.SimpleRatingView;
import org.jetbrains.annotations.NotNull;


/**
 * 自定义download对话框
 *
 * @author DengBin
 */
public class StarViewDialog extends Dialog {

    private static StarViewDialog dialog;
    private static TextView cancelTv;
    private static TextView okTV;


    private RatingBar ratingBar;
    XLHRatingBar ratingBar2;

    private TextView titleTV, ratingDescTv;
    private TextView versionNameTv;
    private static TextView fileSizeTv;
    private TextView describeTv;
    private static LinearLayout btnLayout;

    private static Context mContext;

    private StarViewDialog(Context context) {
        super(context, R.style.myDialog);
        this.setCancelable(true);
    }

    @NotNull
    public static StarViewDialog create(Context context) {
        mContext = context;

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        // onCreate中
        dialog = new StarViewDialog(context);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    public void setContent(final OnBtutonClickListener onOKClick, View.OnClickListener onCancelClick) {

        if (onOKClick != null) {
            okTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ratingValue = (int) ratingBar.getRating();
                    onOKClick.onButtonClick(ratingValue);
                }
            });
        }

        if (onCancelClick != null) {
            cancelTv.setOnClickListener(onCancelClick);
        }
    }

    public static void closeDialog() {
        try {
            if (null != dialog && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_star_view_layout);

//        XLHRatingBar ratingBar2 = findViewById(R.id.ratingBar2);
//        ratingBar2.setRatingView(new SimpleRatingView());
//        ratingBar2.setNumStars(5);
//        ratingBar2.setRating(4);
//        ratingBar2.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
//            @Override
//            public void onChange(float rating, int numStars) {
//                Toast.makeText(getContext(), "rating:" + rating, Toast.LENGTH_SHORT).show();
//            }
//        });


        titleTV = (TextView) findViewById(R.id.title_tick);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingDescTv = (TextView) findViewById(R.id.ratingDescTv);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int ratingValue = (int) rating;
                if (ratingValue == 1) {
                    ratingDescTv.setText("非常不满意，各方面都差");
                } else if (ratingValue == 2) {
                    ratingDescTv.setText("不满意，比较差");
                } else if (ratingValue == 3) {
                    ratingDescTv.setText("一般，还需改善");
                } else if (ratingValue == 4) {
                    ratingDescTv.setText("比较满意，仍可改善");
                } else if (ratingValue == 5) {
                    ratingDescTv.setText("非常满意，无可挑剔");
                }
            }
        });

        cancelTv = (TextView) findViewById(R.id.cancel_down);
        okTV = (TextView) findViewById(R.id.ok_down);
    }

    public interface OnBtutonClickListener {

        void onButtonClick(int ratingValue);
    }
}
