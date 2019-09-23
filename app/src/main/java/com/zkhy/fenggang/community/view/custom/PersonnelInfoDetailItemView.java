package com.zkhy.fenggang.community.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkhy.fenggang.community.R;

public class PersonnelInfoDetailItemView extends LinearLayout {

    private TextView mTitle;
    private TextView mContent;

    public PersonnelInfoDetailItemView(Context context) {
        this(context, null);
    }

    public PersonnelInfoDetailItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonnelInfoDetailItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(context, attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.personnel_info_detail_item_view, this, false);
        this.addView(view);
        mTitle = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PersonnelInfoDetailItemView);
        setTitle(typedArray.getString(R.styleable.PersonnelInfoDetailItemView_itemTitle));
        setContent(typedArray.getString(R.styleable.PersonnelInfoDetailItemView_itemContent));
        typedArray.recycle();
    }

    public void setContent(String content) {
        mContent.setText(content);
    }


    private void setTitle(String title) {
        mTitle.setText(title);
    }
}
