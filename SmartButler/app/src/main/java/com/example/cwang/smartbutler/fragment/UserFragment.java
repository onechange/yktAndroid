/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.fragment
 * 文件名: ButlerFragment
 * 创建者: cwang
 * 创建时间: 2018/7/13 5:04 PM
 */

package com.example.cwang.smartbutler.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.MyUser;
import com.example.cwang.smartbutler.ui.ExpressActivity;
import com.example.cwang.smartbutler.ui.LoginActivity;
import com.example.cwang.smartbutler.ui.PhoneActivity;
import com.example.cwang.smartbutler.utils.L;
import com.example.cwang.smartbutler.utils.ShereUtils;
import com.example.cwang.smartbutler.utils.UtilTools;
import com.example.cwang.smartbutler.view.CustomDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends Fragment implements View.OnClickListener {

    private Button exitBtn;
    private TextView edit_user;

    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;

    private Button btn_update_ok;
    private CircleImageView profile_image;
    private CustomDialog dialog;

    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;

    //快递查询
    private TextView tv_courier;
    //归属地查询
    private TextView tv_phone;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,null);

        findView(view);

        return view;
    }

    private void findView(View view) {

        exitBtn = view.findViewById(R.id.btn_exit_user);
        exitBtn.setOnClickListener(this);
        edit_user = view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);
        tv_courier =  view.findViewById(R.id.tv_courier);
        tv_courier.setOnClickListener(this);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_phone.setOnClickListener(this);

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        et_age = (EditText) view.findViewById(R.id.et_age);
        et_desc = (EditText) view.findViewById(R.id.et_desc);

        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);

        UtilTools.getImgFromShere(getActivity(),profile_image);





        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        //初始化dialog
        dialog  = new CustomDialog(getActivity(),dm.widthPixels,800,R.layout.dialog_photo,R.style.pop_anim_style, Gravity.BOTTOM,0);
        //提示框以外点击无效
        dialog.setCancelable(false);

        btn_camera = dialog.findViewById(R.id.btn_camera);
        btn_picture = dialog.findViewById(R.id.btn_picture);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_camera.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        //设置具体值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);

        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge()+"");
        et_sex.setText(userInfo.isGender() ? "男" : "女");
        et_desc.setText(userInfo.getDesc());

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit_user:
                MyUser.logOut();
                BmobUser currentUser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.edit_user:
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            //编辑资料
            case R.id.btn_update_ok:
                String username = et_username.getText().toString();
                String age = et_age.getText().toString();
                String sex = et_sex.getText().toString();
                String desc = et_desc.getText().toString();

                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(sex)) {
                    //更新属性
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    //性别
                    if (sex.equals("男")){
                        user.setGender(true);
                    }else {
                        user.setGender(false);
                    }
                    //简介
                    if (!TextUtils.isEmpty(desc)){
                        user.setDesc(desc);
                    } else {
                        user.setDesc("什么都没有");
                    }

                    BmobUser bmobuser = BmobUser.getCurrentUser();
                    user.update(bmobuser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                //修改成功
                                btn_update_ok.setVisibility(View.GONE);
                                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(),"不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.profile_image:

                dialog.show();
                break;
            case R.id.btn_cancel:
                L.d("点击了取消");
                dialog.dismiss();
                break;
            case R.id.btn_camera:
                L.d("点击了相机");
                openCamera();
                break;
            case R.id.btn_picture:
                L.d("点击了相册");
                openPicture();
                break;
            case R.id.tv_courier:
                startActivity(new Intent(getActivity(),ExpressActivity.class));
                break;
            case R.id.tv_phone:
                startActivity(new Intent(getActivity(),PhoneActivity.class));
                break;
        }
    }

    private static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int IMAGE_REQUEST_CODE = 101;
    private static final int RESULT_REQUEST_CODE = 102;
    //调用相册
    private void openPicture() {
         Intent intent = new Intent(Intent.ACTION_PICK);
         intent.setType("image/*");
         startActivityForResult(intent,IMAGE_REQUEST_CODE);
         dialog.dismiss();
    }

    //调用相机
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != getActivity().RESULT_CANCELED){
            switch (requestCode){
                case CAMERA_REQUEST_CODE:
                    break;
                case IMAGE_REQUEST_CODE:

                    startPhotoZoom(data.getData());
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null){
                        setImageToView(data);
                        //既然已经设置了图片,我们原先就删除
                    }
                    break;
            }
        }
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null){
             Bitmap bitmap = bundle.getParcelable("data");
             profile_image.setImageBitmap(bitmap);

        }
    }

    //裁剪
    private void startPhotoZoom(Uri data) {
        if (data == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //宽高比例
        intent.putExtra("aspactX",1);
        intent.putExtra("aspactY",1);
        //裁剪图片的质量
        intent.putExtra("outputX",320);
        intent.putExtra("outputY",320);

        startActivityForResult(intent,RESULT_REQUEST_CODE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        保存
        UtilTools.putImgToShere(getActivity(),profile_image);
    }
}
    