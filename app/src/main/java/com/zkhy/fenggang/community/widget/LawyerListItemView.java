package com.zkhy.fenggang.community.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinothk.comm.utils.StringUtil;
import com.sinothk.comm.utils.ToastUtil;
import com.sinothk.view.image.rounded.RoundedImageView;
import com.zkhy.fenggang.community.R;
import com.zkhy.fenggang.community.model.bean.BizUserEntity;
import com.zkhy.library.utils.ImageLoader;

import java.util.List;

/**
 * <pre>
 *  @author 梁玉涛 (https://github.com/sinothk)
 *  @Create 2018/2/10 10:01
 *  @Project: UIPluginLib
 *  @Description: 商城中，产品详情介绍
 *  @Update:
 * <pre>
 */
public class LawyerListItemView extends LinearLayout {

    public LawyerListItemView(Context context) {
        super(context);
    }

    public LawyerListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LawyerListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LawyerListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(final Activity activity, List<BizUserEntity> subList) {

        if (subList == null || subList.size() == 0) {
            return;
        }

        View view = LayoutInflater.from(getContext()).inflate(R.layout.biz_user_list_item, null);

        if (subList.size() == 1) {
            showTab1(activity, view, subList.get(0));

        } else if (subList.size() == 2) {
            showTab1(activity, view, subList.get(0));
            showTab2(activity, view, subList.get(1));

        } else if (subList.size() == 3) {
            showTab1(activity, view, subList.get(0));
            showTab2(activity, view, subList.get(1));
            showTab3(activity, view, subList.get(2));
        }

        this.addView(view);
    }

    private void showTab1(final Activity activity, View view, final BizUserEntity entity) {
        LinearLayout tab1 = view.findViewById(R.id.tab1);
        tab1.setVisibility(View.VISIBLE);

        RoundedImageView tb1AvatarIv = view.findViewById(R.id.tb1AvatarIv);
        ImageLoader.show(getContext(), entity.getPhoto(), R.drawable.mr_tx, tb1AvatarIv);

        TextView tb1NameTv = view.findViewById(R.id.tb1NameTv);
        tb1NameTv.setText(StringUtil.getNotNullValue(entity.getName(), "未填姓名"));

        TextView tb1WorkAgeTv = view.findViewById(R.id.tb1WorkAgeTv);
        tb1WorkAgeTv.setText(StringUtil.getNotNullValue(entity.getWorkingAge(), "资深"));

        TextView tb1WorkSkillTv = view.findViewById(R.id.tb1WorkSkillTv);
        tb1WorkSkillTv.setText(StringUtil.getNotNullValue(entity.getSkill(), "专业人员"));

        tab1.setOnClickListener(v -> {
//                callPhone(activity, entity.getPhone());
            onItemClickListener.onItemClick(entity.getBizId());
        });
    }

    private void showTab2(final Activity activity, View view, final BizUserEntity entity) {
        LinearLayout tab2 = view.findViewById(R.id.tab2);
        tab2.setVisibility(View.VISIBLE);

        RoundedImageView tb2AvatarIv = view.findViewById(R.id.tb2AvatarIv);
        ImageLoader.show(getContext(), entity.getPhoto(), R.drawable.mr_tx, tb2AvatarIv);

        TextView tb2NameTv = view.findViewById(R.id.tb2NameTv);
        tb2NameTv.setText(StringUtil.getNotNullValue(entity.getName(), "未填姓名"));

        TextView tb2WorkAgeTv = view.findViewById(R.id.tb2WorkAgeTv);
        tb2WorkAgeTv.setText(StringUtil.getNotNullValue(entity.getWorkingAge(), "资深"));

        TextView tb2WorkSkillTv = view.findViewById(R.id.tb2WorkSkillTv);
        tb2WorkSkillTv.setText(StringUtil.getNotNullValue(entity.getSkill(), "专业人员"));

        tab2.setOnClickListener(v -> {
//                callPhone(activity, entity.getPhone());
            onItemClickListener.onItemClick(entity.getBizId());
        });

    }

    private void showTab3(final Activity activity, View view, final BizUserEntity entity) {
        LinearLayout tab3 = view.findViewById(R.id.tab3);
        tab3.setVisibility(View.VISIBLE);

        RoundedImageView tb3AvatarIv = view.findViewById(R.id.tb3AvatarIv);
        ImageLoader.show(getContext(), entity.getPhoto(), R.drawable.mr_tx, tb3AvatarIv);

        TextView tb3NameTv = view.findViewById(R.id.tb3NameTv);
        tb3NameTv.setText(StringUtil.getNotNullValue(entity.getName(), "未填姓名"));

        TextView tb3WorkAgeTv = view.findViewById(R.id.tb3WorkAgeTv);
        tb3WorkAgeTv.setText(StringUtil.getNotNullValue(entity.getWorkingAge(), "资深"));

        TextView tb3WorkSkillTv = view.findViewById(R.id.tb3WorkSkillTv);
        tb3WorkSkillTv.setText(StringUtil.getNotNullValue(entity.getSkill(), "专业人员"));

        tab3.setOnClickListener(v -> {
//            callPhone(activity, entity.getPhone());
            onItemClickListener.onItemClick(entity.getBizId());
        });
    }

    private void callPhone(Activity activity, String phoneNum) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show("拨号功能不可用");
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }
}
