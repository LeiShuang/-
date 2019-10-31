package com.zfsoft.zfsoft_product.modules.login.info;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.hengyi.wheelpicker.listener.OnCityWheelComfirmListener;
import com.hengyi.wheelpicker.ppw.CityWheelPickerPopupWindow;
import com.vondear.rxtool.view.RxToast;
import com.zfsoft.zfsoft_product.R;
import com.zfsoft.zfsoft_product.base.BaseFragment;
import com.zfsoft.zfsoft_product.base.HomeActivity;
import com.zfsoft.zfsoft_product.base.MineBottomSheetDialogFragment;
import com.zfsoft.zfsoft_product.common.Config;
import com.zfsoft.zfsoft_product.entity.SignBean;
import com.zfsoft.zfsoft_product.entity.User;
import com.zfsoft.zfsoft_product.utils.AppUtils;
import com.zfsoft.zfsoft_product.utils.DbHelper;
import com.zfsoft.zfsoft_product.utils.FileUtils;
import com.zfsoft.zfsoft_product.utils.ImageLoaderHelper;
import com.zfsoft.zfsoft_product.utils.ImageUtil;
import com.zfsoft.zfsoft_product.utils.KeyboardUtils;
import com.zfsoft.zfsoft_product.utils.SDCardUtils;
import com.zfsoft.zfsoft_product.utils.ToastUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

/**
 * Created by ckw
 * on 2019/1/17.
 */
public class SetInfoFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks
,MineBottomSheetDialogFragment.OnViewClickListener, SetInfoContract.View, View.OnClickListener {

    @Inject
    public SetInfoFragment() {
    }

    @Inject
    SetInfoPresenter mSetInfoPresenter;

    private static final String BOTTOM_SHEET_DIALOG_FRAGMENT = "BOTTOM_SHEET_DIALOG_FRAGMENT";
    private static final int REQUEST_CODE_CAMERA_PERMISSIONS = 2; //拍照时的请求码
    private static final int REQUEST_CODE_IMAGE_FROM_ALBUM = 3; //从相册中选取的请求码
    private static final int REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS = 3; //从相册中选取的权限
    private static final int REQUEST_CODE_TAKE_CAMERA = 4; //拍照
    private static final int REQUEST_CODE_CROP_IMAGE = 5; //裁剪图片的请求码

    @BindView(R.id.tv_save)
    TextView mTvSave;//保存
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;//返回

    @BindView(R.id.iv_user)
    CircleImageView mCircleImageView;//用户头像
    @BindView(R.id.et_user_name)
    EditText mEtUserName;//姓名
    @BindView(R.id.et_nick_name)
    EditText mEtNickName;//昵称
    @BindView(R.id.et_job)
    EditText mEtJob;//职业
    @BindView(R.id.rl_birthday)
    RelativeLayout mRlShowBirthday;//生日选择
    @BindView(R.id.tv_birthday_select)
    TextView mTvShowBirthday;//显示生日
    @BindView(R.id.rl_sex)
    RelativeLayout mRlShowDialog;//性别选择图标
    @BindView(R.id.tv_sex_select)
    TextView mTvShowSex;//性别显示
    @BindView(R.id.rl_address)
    RelativeLayout mRlShowAddress;//选择地址
    @BindView(R.id.tv_city)
    TextView mTvShowAddress;//地址显示
    @BindView(R.id.et_address)
    EditText mEtAddress;//详细地址
    @BindView(R.id.et_my_sign)
    EditText mEtMySign;//我的签名
//    @BindView(R.id.et_number_book)
//    EditText mEtRedBookId;//小红书号
//    @BindView(R.id.et_nick_book)
//    EditText mEtRedBookNick;//小红书昵称
//    @BindView(R.id.et_address_book)
//    EditText mEtRedBookAddress;//小红书主页
//    @BindView(R.id.et_fans_book)
//    EditText mEtRedBookFans;

    private String  mPackageName;
    private File mFile; //裁剪图片保存的位置

//    private int mJumpFrom;//从注册（validationFragment）界面进入为0，从设置界面进入为1
    private int mSexChoice = 0;
    private final String[] items = { "男","女" };

    private TimePickerView mTimePicker;//生日选择器
    private CityWheelPickerPopupWindow wheelPickerPopupWindow;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_set_info;
    }

    @Override
    protected void initVariables() {
        mPackageName = AppUtils.getAppName(getContext());
    }

    @Override
    protected void handleBundle(Bundle bundle) {
    }

    @Override
    protected void operateViews(View view) {
        wheelPickerPopupWindow = new CityWheelPickerPopupWindow(getActivity());
        initLunarPicker();
            mSetInfoPresenter.getUserInfo(Config.HSK,DbHelper.getUserId(mContext));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSetInfoPresenter.dropView();
    }

    @Override
    protected void initListener() {
        mRlShowBirthday.setOnClickListener(this);
        mRlShowAddress.setOnClickListener(this);
        wheelPickerPopupWindow.setListener(new OnCityWheelComfirmListener() {
            @Override
            public void onSelected(String Province, String City, String District, String PostCode) {
                mTvShowAddress.setText(Province + City + District);
            }
        });
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineBottomSheetDialogFragment fragment = MineBottomSheetDialogFragment.newInstance();
                fragment.setOnViewClickListener(SetInfoFragment.this);
                fragment.show(getChildFragmentManager(), BOTTOM_SHEET_DIALOG_FRAGMENT);
            }
        });
        mRlShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideSoftInput(getActivity());
                showSingleChoiceDialog();
            }
        });

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(canSubmit() || mFile != null){
                    //普通参数
                    Map<String,RequestBody> map = buildParams();
                    List<MultipartBody.Part> images = new ArrayList<>();
                    if(mFile != null){
                        images.add(getFilePart("file",mFile,mFile.getName()));
                    }
                    mSetInfoPresenter.uploadPersonalInfo(map,images);
                }else {
                    ToastUtils.showCenterToast(getContext(),"请填写完所有参数");
                }

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
        mSetInfoPresenter.takeView(this);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        switch (requestCode) {
            /*
             * 拍照
             */
            case REQUEST_CODE_CAMERA_PERMISSIONS:
                if (!checkFileIsCreateSuccess()) {
                    return;
                }
                Intent intentCamera = new Intent();
                intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = getImageContentUri(mFile.getAbsolutePath());
                    List<ResolveInfo> resInfoList = getContext().getPackageManager().queryIntentActivities(intentCamera, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        getContext().grantUriPermission(packageName, uri,
                                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                } else {
                    uri = Uri.fromFile(mFile);
                }
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intentCamera, REQUEST_CODE_TAKE_CAMERA);
                break;

            /*
             * 从相册中选取
             */
            case REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS:
                openActivityForPicture();
                break;

            default:
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {

            /*
             * 从相册中选取
             */
            case REQUEST_CODE_IMAGE_FROM_ALBUM:
                if (data != null) {
                    if (!checkFileIsCreateSuccess()) {
                        return;
                    }
                    Uri uri = data.getData();
                    String path = ImageUtil.getImageAbsolutePath(getContext(), uri);
                    if (path == null) {
                        return;
                    }
                    File file = new File(path);
                    cropImage(file);
                }
                break;

            /*
             * 拍照
             */
            case REQUEST_CODE_TAKE_CAMERA:
                cropImage(mFile);
                break;

            /*
             * 裁剪图片
             */
            case REQUEST_CODE_CROP_IMAGE:
                if (mFile == null) {
                    ToastUtils.showCenterToast(getContext(),getResources().getString(R.string.icon_upload_failure));
                    return;
                }
                updateIcon(mFile.getAbsolutePath());

                break;
            case AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE:
                ToastUtils.showCenterToast(getContext(),"用户从App设置界面回来了");
                break;

            default:
                break;
        }

    }

    @Override
    public void onSelectFromAlbumClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            EasyPermissions.requestPermissions(this,
                    getResources().getString(R.string.request_permission_by_select_from_album),
                    REQUEST_CODE_SELECT_FROM_ALBUM_PERMISSIONS, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            openActivityForPicture();
        }
    }

    @Override
    public void onTakePicturesClick() {
        EasyPermissions.requestPermissions(this,
                getResources().getString(R.string.request_permission_by_camera),
                REQUEST_CODE_CAMERA_PERMISSIONS, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    private MultipartBody.Part getFilePart(String partName,File file,String fileName){
        RequestBody requestBody = RequestBody.create(MediaType.parse(Config.UPLOAD.UPLOAD_FILE_FORMAT), file);
        return MultipartBody.Part.createFormData(partName, fileName, requestBody);
    }

    public RequestBody getRequestBody(String para) {
        return RequestBody.create(MediaType.parse(Config.UPLOAD.UPLOAD_FILE_FORMAT),para);
    }

    private boolean canSubmit(){
        if(mEtUserName.getText().toString().equals("") || mEtNickName.getText().toString().equals("")
                || mEtJob.getText().toString().equals("") || mTvShowBirthday.getText().toString().equals("")
                || mTvShowAddress.getText().toString().equals("") || mEtAddress.getText().toString().equals("")
                || mEtMySign.getText().toString().equals("")){
            return false;
        }
        return true;
    }
    /*
     * 构建普通参数
     * */
    private Map<String, RequestBody> buildParams(){

        Map<String,RequestBody> map = new LinkedHashMap<>();
        map.put("hsk",getRequestBody(Config.HSK));
        map.put("userid",getRequestBody(DbHelper.getUserId(mContext)));
        map.put("xm",getRequestBody(mEtUserName.getText().toString().trim()));
        map.put("nc",getRequestBody(mEtNickName.getText().toString().trim()));
        map.put("zy",getRequestBody(mEtJob.getText().toString().trim()));
        map.put("sr",getRequestBody(mTvShowBirthday.getText().toString().trim()));
        map.put("xb",getRequestBody(String.valueOf(mSexChoice)));

        map.put("szdq",getRequestBody(mTvShowAddress.getText().toString().trim()));
        map.put("dz",getRequestBody(mEtAddress.getText().toString().trim()));
        map.put("grjj",getRequestBody(mEtMySign.getText().toString().trim()));

        //选填
//        map.put("redAccount",getRequestBody(mEtRedBookId.getText().toString().trim()));
//        map.put("redName",getRequestBody(mEtRedBookNick.getText().toString().trim()));
//        map.put("redHome",getRequestBody(mEtRedBookAddress.getText().toString().trim()));
//        map.put("redFans",getRequestBody(mEtRedBookFans.getText().toString().trim()));

        return map;
    }


    /*
     * 选择性别
     * */
    private void showSingleChoiceDialog(){

        mSexChoice = 0;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(getContext());
        singleChoiceDialog.setTitle("性别");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSexChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTvShowSex.setText(items[mSexChoice]);

                    }
                });
        singleChoiceDialog.show();
    }


    /*
    * 更新头像图片
    * */
    private void updateIcon(String path) {
        Glide.with(this).load(mFile).asBitmap().centerCrop().into(mCircleImageView);
//        ImageLoaderHelper.loadHeadImage(mContext, mCircleImageView, path);
    }

    /*
    * 裁剪图片
    * */
    private void cropImage(File cropFile) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = getImageContentUri(cropFile.getAbsolutePath());
        } else {
            uri = Uri.fromFile(cropFile);
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", getResources().getDimensionPixelSize(R.dimen.circle_image_view_size));
        intent.putExtra("outputY", getResources().getDimensionPixelSize(R.dimen.circle_image_view_size));
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    /*
    * 创建新文件
    * */
    private File createNewFile() {
        if (!SDCardUtils.isSDCardEnable()) {
            ToastUtils.showCenterToast(getContext(),getResources().getString(R.string.please_insert_sdcard));
            return null;
        }
        String take_picture_dir = SDCardUtils.getSDCardPath() + mPackageName + "/camera/";
        if (!FileUtils.createOrExistsDir(take_picture_dir)) {
            ToastUtils.showCenterToast(getContext(),getResources().getString(R.string.dir_create_failure));
            return null;
        }
        String take_picture_image_path = take_picture_dir + System.currentTimeMillis() + ".png";
        File file = new File(take_picture_image_path);
        if (!FileUtils.createFileByDeleteOldFile(file)) {
            ToastUtils.showCenterToast(getContext(),getResources().getString(R.string.dir_create_failure));
            return null;
        }
        return file;
    }

    /*
    * 判断文件是否创建成功
    * */
    public boolean checkFileIsCreateSuccess() {
        mFile = createNewFile();
        return mFile != null;
    }

    /**
     * 适配7.0权限方法
     * @param path
     * @return
     */
    private Uri getImageContentUri(String path){
        Cursor cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, ""+id);
        }else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, path);
            return getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    /*
     * 从相册中选取
     * */
    private void openActivityForPicture(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_IMAGE_FROM_ALBUM);
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 生日选择器
     */
    private void initLunarPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2100, 2, 28);
        //时间选择器 ，自定义布局
        mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvShowBirthday.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mTimePicker.returnData();
                                mTimePicker.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mTimePicker.dismiss();
                            }
                        });

                    }

                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }


    @Override
    public void uploadInfoSuccess(SignBean signBean) {
        String msgtype = signBean.getMsgtype();
        if(msgtype.equals("1")){
            ToastUtils.showCenterToast(getContext(),"上传成功");
            getActivity().finish();
        }else {
            ToastUtils.showCenterToast(getContext(),"操作失败");
        }
    }

    @Override
    public void uploadInfoFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void getUserInfoSuccess(User user) {
        if(!user.getMyimg().isEmpty()){
            ImageLoaderHelper.loadHeadImage(mContext,mCircleImageView,user.getMyimg());
        }
        if(user.getXm() != null){
            mEtUserName.setText(user.getXm());
        }
       if(user.getNc() != null){
           mEtNickName.setText(user.getNc());
       }
        if(user.getZy() != null){
            mEtJob.setText(user.getZy());
        }
        if(user.getSr() != null){
            mTvShowBirthday.setText(user.getSr());
        }

        if(user.getXb() != null){
            if(user.getXb().equals("0")){
                mTvShowSex.setText("男");
            }else if(user.getXb().equals("1")){
                mTvShowSex.setText("女");
            }else {
                mTvShowSex.setText("未知");
            }
        }

        if(user.getSzdq() != null){
            mTvShowAddress.setText(user.getSzdq());
        }
        if(user.getDz() != null){
            mEtAddress.setText(user.getDz());
        }
        if(user.getGrjj() != null){
            mEtMySign.setText(user.getGrjj());
        }

//        if(user.getRedAccount() != null){
//            mEtRedBookId.setText(user.getRedAccount());
//        }
//        if(user.getRedName() != null){
//            mEtRedBookNick.setText(user.getRedName());
//        }
//
//        if(user.getRedHome() != null){
//            mEtRedBookAddress.setText(user.getRedHome());
//        }
//
//        if(user.getRedFans() != null){
//            mEtRedBookFans.setText(user.getRedFans());
//        }



    }

    @Override
    public void getUserInfoFailure(String errorMsg) {
        ToastUtils.showCenterToast(getContext(),errorMsg);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.rl_birthday:
                KeyboardUtils.hideSoftInput(getActivity());
                mTimePicker.show();
                break;
            case R.id.rl_address:
                KeyboardUtils.hideSoftInput(getActivity());
                wheelPickerPopupWindow.show();
                break;
        }
    }
}