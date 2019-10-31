package com.zfsoft.zfsoft_product.modules.report.submit_report;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.mingle.widget.LoadingView;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.demo.common.utils.TCConstants;
import com.tencent.liteav.demo.common.view.VideoWorkProgressFragment;
import com.tencent.liteav.demo.videorecord.TCVideoRecordActivity;
import com.tencent.rtmp.TXVodPlayConfig;
import com.tencent.rtmp.TXVodPlayer;
import com.vondear.rxtool.RxLogTool;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.modules.report.videopublish.server.PublishSigListener;
import com.zfsoft.zfsoft_product.modules.report.videopublish.server.ReportVideoInfoListener;
import com.zfsoft.zfsoft_product.modules.report.videopublish.server.VideoDataMgr;
import com.zfsoft.zfsoft_product.modules.report.videoupload.TXUGCPublish;
import com.zfsoft.zfsoft_product.modules.report.videoupload.TXUGCPublishTypeDef;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.tencent.rtmp.TXLivePlayer.TAG;
import static com.zfsoft.zfsoft_product.modules.report.submit_report.SubmitReportActivity.IMAGE_ITEM_ADD;

/**
 * 创建日期：2019/1/23 on 11:26
 * 描述:
 * 作者:Ls
 */
public class SubmitReportFragment extends BaseFragment implements View.OnClickListener, SubmitReportAdapter.OnRecyclerViewItemClickListener, SubmitReportContract.View {
    @BindView(R.id.submit_report_back)
    ImageView mSubmitReportBack;
    @BindView(R.id.submit_recycler)
    RecyclerView mSubmitRecycler;
    @BindView(R.id.tv_sum_title)
    TextView mTvSumTitle;
    @BindView(R.id.et_input_title)
    EditText mEtInputTitle;
    @BindView(R.id.et_input_content)
    EditText mEtInputContent;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.tv_showPic)
    TextView mTvShowPic;
    @BindView(R.id.iv_video_pic)
    ImageView mIvVideoPic;
    @BindView(R.id.rl_video_pic)
    RelativeLayout mRlVideoPic;
    @BindView(R.id.video_player)
    StandardGSYVideoPlayer mVideoPlayer;
    @BindView(R.id.submit_loading_view)
    LoadingView mSubmitLoadingView;



    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private static final int REQUEST_CODE_VEDIO = 102;
    private SubmitReportAdapter mAdapter;
    private String mProductId;
    private String mTitle;
    private String mContent;
    private String mVedioPath;
    private String mVedioPic;
    private String mMp4Url;
    private String mPicUrl;


    @Inject
    public SubmitReportFragment() {

    }

    @Inject
    SubmitReportPresenter mPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_submit_report;
    }

    @Override
    protected void initVariables() {
        selImageList = new ArrayList<>();
    }

    @Override
    protected void handleBundle(Bundle bundle) {
        mProductId = bundle.getString("thingsId", "1");
    }

    @Override
    protected void operateViews(View view) {
        mAdapter = new SubmitReportAdapter(maxImgCount, mContext, selImageList);
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mSubmitRecycler.setLayoutManager(manager);
        mSubmitRecycler.setAdapter(mAdapter);
        mSubmitRecycler.setHasFixedSize(true);
        mAdapter.setOnItemClickListener(this);

    }

    private int num = 30;

    @Override
    protected void initListener() {
        mSubmitReportBack.setOnClickListener(this);
        mTvSumTitle.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mEtInputTitle.addTextChangedListener(new TextWatcher() {
            private CharSequence wordNumber;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordNumber = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                mTvSumTitle.setText("" + number);
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter.takeView(this);
    }

    private MultipartBody.Part getFilePart(String partName, File file, String fileName) {
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.UPLOAD.UPLOAD_FILE_FORMAT), file);
        return MultipartBody.Part.createFormData(partName, fileName, requestBody);
    }

    public RequestBody getRequestBody(String para) {
        return RequestBody.create(MediaType.parse(Config.UPLOAD.UPLOAD_FILE_FORMAT), para);
    }

    @Override
    public void onClick(View v) {
        int key = v.getId();
        if (key == R.id.submit_report_back) {
            getActivity().finish();
        } else if (key == R.id.tv_submit) {
            submitPics();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private int type;

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("相册");
                names.add("小视频");


                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                //相册
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                type = 1;
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
                              //  intent1.putExtra(ImageGridActivity.)
                                //intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;

                            case 1:
                                //小视频
                                type = 2;
                                Intent intent = new Intent(getActivity(), TCVideoRecordActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_VEDIO);
                                break;

                            default:
                                break;
                        }
                    }
                }, names);
                break;

            default:
                //打开预览
                type = 1;
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    ArrayList<ImageItem> images = null;
    private String mCoverImagePath;//
    private TXVodPlayer mTXVodPlayer;
    private TXVodPlayConfig mTXPlayConfig = null;
    private TXUGCPublish mTXugcPublish;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    mAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    mAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_VEDIO) {
                mVedioPath = data.getStringExtra(TCConstants.VIDEO_EDITER_PATH);
                mVedioPic = data.getStringExtra(TCConstants.VIDEO_RECORD_COVERPATH);

              /*  mCoverImagePath = "/sdcard/cover.jpg";
                final Bitmap coverBitmap = TXVideoInfoReader.getInstance().getSampleImage(0, mVedioPath);

                if (coverBitmap != null) {
                    mIvVideoPic.setImageBitmap(coverBitmap);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            saveBitmap(coverBitmap, mCoverImagePath);
                        }
                    }).start();
                }*/
                mTXVodPlayer = new TXVodPlayer(mContext);
                mTXPlayConfig = new TXVodPlayConfig();
                //拿到视频数据和照片数据回来之后开始上传到腾讯云服务器
//                mVideoPublish = new TXUGCPublish(mContext.getApplicationContext());
                mTXugcPublish = new TXUGCPublish(mContext.getApplicationContext(), "customID");
                initVideoListener();
                if (mWorkLoadingProgress == null) {
                    initWorkLoadingProgress();
                }
                mWorkLoadingProgress.setProgress(0);
                mWorkLoadingProgress.show(getActivity().getSupportFragmentManager(), "progress_dialog");
                getPulishSig();
                isCancelPublish = false;
/*// 文件发布默认是采用断点续传
                TXUGCPublishTypeDef.TXPublishParam param = new TXUGCPublishTypeDef.TXPublishParam();
                param.signature = mCosSignature;                        // 需要填写第四步中计算的上传签名
// 录制生成的视频文件路径, ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
                param.videoPath = mVedioPath;
// 录制生成的视频首帧预览图，ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
                param.coverPath = vedioPic;
                mVideoPublish.publishVideo(param);*/

            }

        }
    }

    /**
     * 获取秘钥
     */
    private void getPulishSig() {
        VideoDataMgr.getInstance().getPublishSig();
    }

    private boolean isCancelPublish;

    private void initWorkLoadingProgress() {
        if (mWorkLoadingProgress == null) {
            mWorkLoadingProgress = VideoWorkProgressFragment.newInstance("视频生成中...");
            mWorkLoadingProgress.setOnClickStopListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTXugcPublish != null) {
                        mTXugcPublish.canclePublish();
                        isCancelPublish = true;
                        mWorkLoadingProgress.setProgress(0);
                        mWorkLoadingProgress.dismiss();
                    }
                }
            });
        }
        mWorkLoadingProgress.setProgress(0);
    }

    private ReportVideoInfoListener mReportVideoInfoListener;
    private VideoWorkProgressFragment mWorkLoadingProgress; // 进度
    private String signature;
    private PublishSigListener mPublishSiglistener;

    private void initVideoListener() {
        mPublishSiglistener = new PublishSigListener() {
            @Override
            public void onSuccess(String signatureStr) {
                signature = signatureStr;
                publish();
            }

            @Override
            public void onFail(final int errCode) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mWorkLoadingProgress != null && mWorkLoadingProgress.isAdded()) {
                            mWorkLoadingProgress.dismiss();
                        }
                        ToastUtils.showCenterToast(getContext(), "签名获取失败");
                    }
                });
            }
        };
        VideoDataMgr.getInstance().setPublishSigListener(mPublishSiglistener);
        mReportVideoInfoListener = new ReportVideoInfoListener() {
            @Override
            public void onFail(int errCode) {
                TXCLog.e(TAG, "reportVideoInfo, report video info fail");
            }

            @Override
            public void onSuccess() {
                TXCLog.i(TAG, "reportVideoInfo, report video info success");
            }
        };
        VideoDataMgr.getInstance().setReportVideoInfoListener(mReportVideoInfoListener);
    }

    private void publish() {
        mTXugcPublish.setListener(new TXUGCPublishTypeDef.ITXVideoPublishListener() {
            @Override
            public void onPublishProgress(long uploadBytes, long totalBytes) {
                RxLogTool.e("onPublishProgress [" + uploadBytes + "/" + totalBytes + "]");
                if (isCancelPublish) {
                    return;
                }
                mWorkLoadingProgress.setProgress((int) ((uploadBytes * 100) / totalBytes));
            }

            @Override
            public void onPublishComplete(TXUGCPublishTypeDef.TXPublishResult result) {
                RxLogTool.e("onPublishComplete [" + result.retCode + "/" + (result.retCode == 0 ? result.videoURL : result.descMsg) + "]");
                if (mWorkLoadingProgress != null && mWorkLoadingProgress.isAdded()) {
                    mWorkLoadingProgress.dismiss();
                }

                if (isCancelPublish) {
                    return;
                }

                // 这里可以把上传返回的视频信息以及自定义的视频信息上报到自己的业务服务器
                reportVideoInfo(result);


            }
        });

        TXUGCPublishTypeDef.TXPublishParam param = new TXUGCPublishTypeDef.TXPublishParam();
        // signature计算规则可参考 https://www.qcloud.com/document/product/266/9221
        param.signature = signature;
        param.videoPath = mVedioPath;
        param.coverPath = mVedioPic;
        mTXugcPublish.publishVideo(param);
    }

    private String mRealMp4Url;

    private void reportVideoInfo(TXUGCPublishTypeDef.TXPublishResult result) {
        mSubmitRecycler.setVisibility(View.GONE);
        mVideoPlayer.setVisibility(View.VISIBLE);
        mRlVideoPic.setVisibility(View.VISIBLE);
        mMp4Url = result.videoURL;//视频url地址
        mRealMp4Url = mMp4Url;
        if (mMp4Url.startsWith("http://")) {
            mRealMp4Url = mMp4Url.replace("http://", "https://");
        }
        mPicUrl = result.coverURL;//图片url地址

        mVideoPlayer.setUp(mRealMp4Url, true, "精彩视频");
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_16_9);
        ImageLoaderHelper.loadImage(mContext, mIvVideoPic, mPicUrl);

    }

    public static void saveBitmap(Bitmap bitmap, String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public void submitPicSuccess(SignBean info) {
        mTvSubmit.setEnabled(true);
        String picType = info.getMsgtype();
        if ("1".equals(picType)) {
            mSubmitLoadingView.setVisibility(View.GONE);
            ToastUtils.showCenterToast(getContext(), mContext.getResources().getString(R.string.submit_report_success));
            getActivity().setResult(1001);//返回待提交界面时重新刷新
            getActivity().finish();
        } else if ("2".equals(picType)) {
            mSubmitLoadingView.setVisibility(View.GONE);
            ToastUtils.showCenterToast(getContext(), mContext.getResources().getString(R.string.submit_report_failed));
        }


    }

    @Override
    public void submitPicsFailed(String errorMsg) {
        mTvSubmit.setEnabled(true);
        mSubmitLoadingView.setVisibility(View.GONE);
        ToastUtils.showCenterToast(getContext(), mContext.getResources().getString(R.string.submit_report_failed));
    }

    private TXUGCPublish mVideoPublish;

    @Override
    public void submitPics() {

        mTvSubmit.setEnabled(false);
        if (type == 2) {
            mTitle = mEtInputTitle.getText().toString().trim();
            if (TextUtils.isEmpty(mTitle)) {
                ToastUtils.showCenterToast(getContext(), getResources().getString(R.string.please_input_title_first));
                return;
            }

            mContent = mEtInputContent.getText().toString().trim();
            if (TextUtils.isEmpty(mContent)) {
                ToastUtils.showCenterToast(getContext(), getResources().getString(R.string.please_input_content_first));
                return;
            }
            mSubmitLoadingView.setLoadingText(mContext.getString(R.string.submit_report_wait));
            mSubmitLoadingView.setVisibility(View.VISIBLE);

            Map<String, RequestBody> map = buildParams();
            List<MultipartBody.Part> fileArr = new ArrayList<>();
            mPresenter.submitReportPics(map, fileArr);

        } else if (type == 1) {
            mTitle = mEtInputTitle.getText().toString().trim();
            if (TextUtils.isEmpty(mTitle)) {
                ToastUtils.showCenterToast(getContext(), getResources().getString(R.string.please_input_title_first));
                return;
            }

            mContent = mEtInputContent.getText().toString().trim();
            if (TextUtils.isEmpty(mContent)) {
                ToastUtils.showCenterToast(getContext(), getResources().getString(R.string.please_input_content_first));
                return;
            }

            mSubmitLoadingView.setLoadingText(mContext.getString(R.string.submit_report_wait));
            mSubmitLoadingView.setVisibility(View.VISIBLE);

            Map<String, RequestBody> map = buildParams();
            ArrayList<File> files = new ArrayList<>();
            if (selImageList.size() > 0) {
                for (int i = 0; i < selImageList.size(); i++) {
                    String path = selImageList.get(i).path;
                    File file = new File(path);
                    files.add(file);
                }
            }

            List<MultipartBody.Part> fileArr = new ArrayList<>();
            int size = files.size();
            for (int i = 0; i < files.size(); i++) {
                long l = System.currentTimeMillis();
                String name = selImageList.get(i).name;
                fileArr.add(getFilePart("fileArr", files.get(i), selImageList.get(i).name));
            }

            mPresenter.submitReportPics(map, fileArr);
        }

    }

    /**
     * 封装参数
     */
    private Map<String, RequestBody> buildParams() {
        if (type == 1) {
            Map<String, RequestBody> map = new LinkedHashMap<>();
            map.put("hsk", getRequestBody(Config.HSK));
            map.put("commodityid", getRequestBody(mProductId));
            map.put("type", getRequestBody("1"));
            map.put("userid", getRequestBody(DbHelper.getUserId(mContext)));
            map.put("title", getRequestBody(mTitle));
            map.put("content", getRequestBody(mContent));
            return map;

        } else {
            Map<String, RequestBody> map = new LinkedHashMap<>();
            map.put("hsk", getRequestBody(Config.HSK));
            map.put("commodityid", getRequestBody(mProductId));
            map.put("type", getRequestBody("2"));
            map.put("videopath", getRequestBody(mRealMp4Url));
            map.put("picpath", getRequestBody(mPicUrl));
            map.put("userid", getRequestBody(DbHelper.getUserId(mContext)));
            map.put("title", getRequestBody(mTitle));
            map.put("content", getRequestBody(mContent));
            return map;
        }


    }

}