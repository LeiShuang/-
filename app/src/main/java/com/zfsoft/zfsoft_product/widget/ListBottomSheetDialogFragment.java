package com.zfsoft.zfsoft_product.widget;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.entity.InfoServer;
import com.zfsoft.zfsoft_product.modules.report.report_detail.ReportDetailAdapter;
import com.zfsoft.zfsoft_product.utils.KeyboardUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import static com.zfsoft.zfsoft_product.utils.ScreenUtils.getScreenHeight;

/**
 * 创建日期：2019/1/14 on 9:19
 * 描述:评论框自定义Fragment
 * @extends  BottomSheetDialogFragment
 * 作者:Ls
 */
public class ListBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给Dialog设置背景颜色
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
    }

    /**
     * 如果想要点击外部消失的话 重写此方法
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //设置点击外部可消失
        dialog.setCanceledOnTouchOutside(true);
        //设置使软键盘弹出的时候dialog不会被顶起
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        win.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);
        //这里设置dialog的进出动画
        win.setWindowAnimations(R.style.DialogBottomAnim);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里将view的高度设置为精确高度，即可屏蔽向上滑动不占全屏的手势。
        //如果不设置高度的话 会默认向上滑动时dialog覆盖全屏
        View view = inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (getScreenHeight(getActivity()) / 5) * 4));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottom_recycler);
        final ImageView ivClose = (ImageView)view.findViewById(R.id.iv_discuss_back);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      /*  ReportDetailAdapter adapter = new ReportDetailAdapter(InfoServer.getDiscussInfos(5,3));
        recyclerView.setAdapter(adapter);*/
        EditText etInput = view.findViewById(R.id.et_discuss);
        etInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出评论输入框
               /* InputDialog inputDialog = new InputDialog(getActivity());
                Window window = inputDialog.getWindow();

                WindowManager.LayoutParams params = window.getAttributes();
                //设置软键盘通常是可见的
                window.setSoftInputMode(params.SOFT_INPUT_STATE_VISIBLE);
                inputDialog.show();*/
                showCommentDiscuss();
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    private BottomSheetDialog mDialog;
    private void showCommentDiscuss(){
        mDialog = new BottomSheetDialog(getContext(),R.style.dialog_soft_input);
        View commentView = LayoutInflater.from(getContext()).inflate(R.layout.common_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint(getString(R.string.please_input_discuss));
        mDialog.setContentView(commentView);
        /**
         * 解决BoottomSheetDialog显示不全的问题
         * */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());


        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    mDialog.dismiss();
                    KeyboardUtils.hideInput(getContext(),commentText);
                    // presenter.addDiscuss(mId,commentContent,"");

                }else {
                    ToastUtils.showCenterToast(getContext(),"评论内容不能为空");
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s) && s.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s) && s.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
        });
        mDialog.show();
    }

}