package com.zkhy.community.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.sinothk.comm.utils.ToastUtil;
import com.sinothk.rxretrofit.RxRetrofit;
import com.sinothk.widget.loadingRecyclerView.listeners.ItemClickCallBack;
import com.zkhy.community.R;
import com.zkhy.community.model.api.AllApi;
import com.zkhy.community.model.api.BaseData;
import com.zkhy.community.model.api.ServerConfig;
import com.zkhy.community.model.bean.DictionaryEntity;
import com.zkhy.community.view.main.am.adapter.AMLawQAClassListAdapter;
import com.zkhy.library.widget.decorations.RecyclerLineView;
import org.jetbrains.annotations.Nullable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;

public class LawClassListItemPopupWindow extends PopupWindow {

    RecyclerView recyclerView;
    AMLawQAClassListAdapter adapter;

    public LawClassListItemPopupWindow(Context context, String code) {
        super(context);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//Color.parseColor("#88000000")
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list_item_4_law_q_a_class, null, false);
        setContentView(contentView);

        LinearLayout popView = contentView.findViewById(R.id.popView);
        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LawClassListItemPopupWindow.this.dismiss();
            }
        });

        // 数据部分
        recyclerView = contentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecyclerLineView(context, R.color.app_bg));

        adapter = new AMLawQAClassListAdapter(new ArrayList());
        recyclerView.setAdapter(adapter);

//        adapter.setClickCallBack(new ItemClickCallBack() {
//            @Override
//            public void onItemClick(int i, Object o) {
////                DictionaryEntity entity = (DictionaryEntity) o;
////                ToastUtil.show(entity.getName() + "___" + entity.getCode());
//
//                callBack.onItemClick(i, o);
//            }
//        });

        loadData(code);
    }

    private void loadData(String code) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
                .create(AllApi.class)
                .loadDictionaryChild(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseData<ArrayList<DictionaryEntity>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.show(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<ArrayList<DictionaryEntity>> result) {
                        if (result.getErrcode() == 0) {
                            ArrayList<DictionaryEntity> allData = result.getData();
                            allData.add(0, new DictionaryEntity(DictionaryEntity.ALL_CODE, "不限定"));
                            adapter.setData(allData);
                        } else {
                            ToastUtil.show(result.getErrmsg());
                        }
                    }
                });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);// 以屏幕 左上角 为参考系的
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;  //屏幕高度减去 anchor 的 bottom
            setHeight(h);// 重新设置PopupWindow高度
        }
        super.showAsDropDown(anchor);
    }

    public void setItemClickCallBack(ItemClickCallBack callBack) {
        if (adapter != null && callBack != null) {
            adapter.setClickCallBack(callBack);
        }
    }
}