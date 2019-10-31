package com.zfsoft.zfsoft_product.modules.personal.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.entity.MessageItemBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ckw
 * on 2019/1/19.
 */
public class MessageFragment extends BaseFragment {

    @Inject
    public MessageFragment() {
    }

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.refresh_message)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;

    private MessageAdapter messageAdapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {
        List<MessageItemBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MessageItemBean messageItemBean = new MessageItemBean();
            messageItemBean.setImgUrl("http://dpic.tiankong.com/a6/rz/QJ8813728866.jpg");
            messageItemBean.setTime("星期六");
            messageItemBean.setTitle("通知消息");
            messageItemBean.setContent("你的新人礼包已经到达，请注意查收");
            list.add(messageItemBean);
        }
        mRefreshLayout.setEnableRefresh(false);
        mRvMessage.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        messageAdapter = new MessageAdapter(list);
        mRvMessage.setAdapter(messageAdapter);
    }

    @Override
    protected void initListener() {
        messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
        if (immersionBar == null) {
            return;
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.statusBarColor(R.color.colorWhite)
                .init();
    }
}