package me.happy.win3win.menu;

import android.Manifest;
import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;
import me.happy.win3win.R;
import me.happy.win3win.recyclerview.ResumeAdapter;
import me.happy.win3win.recyclerview.TwoString;
import me.happy.win3win.userdb.UserDBManager;
import me.happy.win3win.databinding.MyresumeBinding;

/**
 * Created by JY on 2017-04-11.
 */

public class MyResumeFragment extends android.support.v4.app.Fragment {

    private MyresumeBinding binding;

    private ResumeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<TwoString> twoStringSet;
    private String[] itemName; //항목 이름들
    private String[] itemContent; //항목 내용들
    private String[] columns; //데이터베이스 컬럼

    private Cursor c;

    private Uri selectedUri; //선택한 사진

    public RequestManager mGlideRequestManager;
    private UserDBManager mDBManager ;
    private FragmentManager fgManager;

    public MyResumeFragment() {
    }

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.myresume, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        binding.rvMyresume.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvMyresume.setLayoutManager(mLayoutManager);

        adapter = new ResumeAdapter(getActivity(), twoStringSet, R.layout.item_myresume);
        binding.rvMyresume.setAdapter(adapter);
        binding.rvMyresume.setItemAnimator(new DefaultItemAnimator());


        setResumeData(); //데이터 설정

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlideRequestManager = Glide.with(getActivity()); //glide
        mDBManager = UserDBManager.getInstance(getActivity()); //dbmanager
        twoStringSet = new ArrayList<>();
    }


    /*data data */
    public void setResumeData() {
        columns = new String[]{"proimg", "name", "birth", "wantjob", "specialnote", "certificate", "edu", "address", "etccareer", "phone"};

        itemName = new String[]{getString(R.string.wantjob), getString(R.string.specialnote), getString(R.string.certificate)
                , getString(R.string.education), getString(R.string.address), getString(R.string.etccareer), getString(R.string.phone)};

        itemContent = new String[columns.length - 1]; //except proimg

       c = mDBManager.query(columns, null, null, null, null, null);

        if (c != null && c.moveToFirst() ) {
            /*
            get profile img. and set profileimg
            */

                byte[] img = c.getBlob(0);
                    if(img !=null){
                    Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
                    binding.imgProfile.setImageBitmap(bmp);
            }

            for (int i = 0; i < itemContent.length ; i++) {
                itemContent[i] = c.getString(i + 1);
            }
            binding.tvProfileName.setText(itemContent[0]);
            binding.tvProfileAge.setText(countAge(itemContent[1]) + " 세");

        }
        c.close();

        for (int i = 0; i < itemName.length; i++) { //데이터 넣어주기
            twoStringSet.add(new TwoString(itemName[i], itemContent[i + 2])); //itemcontent is start wantjob
        }

        adapter.setItems(twoStringSet);
        adapter.notifyDataSetChanged();
    }

    /*
    count age
    */
    public String countAge(String birth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar today = Calendar.getInstance(Locale.KOREA);
        Calendar birthday = Calendar.getInstance();

        String age = "";

        try {
            birthday.setTime(dateFormat.parse(birth));

            if ((today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)  //생일지남
                    && today.get(Calendar.DATE) <= birthday.get(Calendar.DATE)) ||
                    today.get(Calendar.MONTH) > birthday.get(Calendar.MONTH)) {

                age = String.valueOf(today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR));
            } else
                age = String.valueOf(today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) - 1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return age;
    }

    /*
    when click profile edit btn
    */
    @OnClick(R.id.btn_profileEdit)
    public void goProfileEdit() {

        fgManager = getFragmentManager();
        fgManager
                .beginTransaction()
                .replace(R.id.frag, new InfoEditFragment())
                .addToBackStack(null) //saved state
                .commit();
    }

    /*
    click profile image, check sdk version and check permission
    */
    @OnClick(R.id.img_profile)
    public void setProfileImg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPermission();
    }

    /*
    get permission camera and gallery
    */
    public void checkPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() { //if granted permission then show tem bottom picker.
                showTedBottomPicker();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                return;
            }
        };
        new TedPermission(getActivity())
                .setPermissionListener(permissionListener)
                .setRationaleMessage(R.string.rationaleMessage)
                .setDeniedMessage(R.string.deniedMessage)
                .setGotoSettingButtonText(R.string.gotoSettingBottom)
                .setPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    /*show bottom picker this library extends bottomsheetdialogfragment*/
    protected void showTedBottomPicker() {

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity())
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(final Uri uri) {
                        selectedUri = uri;

                        binding.imgProfile.post(new Runnable() {
                            @Override
                            public void run() {
                                mGlideRequestManager
                                        .load(selectedUri)
                                        .into(binding.imgProfile); //set image
                            }
                        });
                        saveImg(uri); //save img db
                    }

                })
                .setPeekHeight(getResources().getDisplayMetrics().heightPixels / 2)
                .setSelectedUri(selectedUri)
                .create();

        tedBottomPicker.show(getFragmentManager()); //show picker
    }

    /*
    save img into db
    */
    protected void saveImg(Uri selectedUri) {
        byte[] imgProfile = getBytes(selectedUri);

        ContentValues contentValues = new ContentValues();
        contentValues.put("proimg", imgProfile);

        mDBManager.update(contentValues, "_id =?", new String[]{"1"});
    }

    /*
    convert uri to bytearray
    */

    public byte[] getBytes(Uri selectedUri) {
        ByteArrayOutputStream bos = null;

        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(selectedUri);

            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();

            for (int len; (len = inputStream.read(buffer)) != -1; ) {
                bos.write(buffer, 0, len);
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos != null ? bos.toByteArray() : null;
    }

    @Override
    public void onPause() {
        twoStringSet.clear();
        super.onPause();
    }

}



