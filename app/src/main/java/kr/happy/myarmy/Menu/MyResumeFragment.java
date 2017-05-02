package kr.happy.myarmy.Menu;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.ItemResumenInfo;
import kr.happy.myarmy.Recyclerview.ResumeAdapter;
import kr.happy.myarmy.UserDB.UserDBManager;

/**
 * Created by JY on 2017-04-11.
 */

public class MyResumeFragment extends android.support.v4.app.Fragment {

    @Nullable
    @BindView(R.id.rv_myresume)
    RecyclerView mRecyclerview;

    @Nullable
    @BindView(R.id.img_profile)
    CircleImageView img_profile;


    @BindString(R.string.wantjob)
    String wantJob;

    @BindString(R.string.specialnote)
    String specialNote;

    @BindString(R.string.certificate)
    String certificate;

    @BindString(R.string.education)
    String edu;

    @BindString(R.string.address)
    String living;

    @BindString(R.string.etccareer)
    String etcCareer;

    private ResumeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemResumenInfo> dataSet;
    private String[] itemName; //항목 이름들
    private String[] itemContent; //항목 내용들
    private String[] columns; //데이터베이스 컬럼

    FragmentManager fgManager;

    private Uri cameraImageUri;
    private Uri selectedUri; //선택한 사진
    public RequestManager mGlideRequestManager;
    public UserDBManager mDBManager=null;

    public MyResumeFragment() {
    }

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.myresume, container, false);
        ButterKnife.bind(this, view);

        setData(); //데이터 설정

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new ResumeAdapter(getActivity(), dataSet, R.layout.item_myresume, itemName.length); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlideRequestManager = Glide.with(getActivity());
        mDBManager=UserDBManager.getInstance(getActivity()); //dbmanager

        columns=new String[]{"name", "birth", "wantjob", "certificate","specialnote", "edu", "address", "etccareer", "phone"};
        itemContent=new String[6];

        Cursor c=mDBManager.query(columns, null, null, null, null, null);

        if(c !=null){
            int i=0;
            while(c.moveToNext())
                itemContent[i++]= c.getString( i+3);
        }
    }

    @Override
    public void onDestroy() {
        Log.d("jy", "myrewume destory");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("jy", "myrewume detach");
        super.onDetach();
    }

    /*temp data */
    public void setData() {
        itemName=new String[]{wantJob, specialNote, certificate, edu, living, etcCareer};
        dataSet = new ArrayList<ItemResumenInfo>();

        for (int i = 0; i < itemName.length; i++) { //임시 실험데이터
            dataSet.add(new ItemResumenInfo(itemName[i], itemContent[i]));

        }
    }


    /*when click profile edit btn*/
    @OnClick(R.id.btn_profileEdit)
    public void profileEdit() {

        fgManager = getFragmentManager();
        fgManager
                .beginTransaction()
                .add(R.id.frag, new InfoEditFragment())
                .addToBackStack(null) //saved state
                .commit();
    }

    /* click profile image, check sdk version and check permission*/
    @OnClick(R.id.img_profile)
    public void SetProfileImg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            CheckPermission();
    }

    /*get permission camera and gallery*/
    public void CheckPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() { //if granted permission then show tem bottom picker.
                ShowTedBottomPicker();
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
    protected void ShowTedBottomPicker() {

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity())
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(final Uri uri) {
                        selectedUri = uri;

                        img_profile.post(new Runnable() {
                            @Override
                            public void run() {
                                mGlideRequestManager
                                        .load(selectedUri)
                                        .into(img_profile); //set image
                                Log.d("jy", selectedUri.toString());
                            }
                        });
                    }
                })
                .setPeekHeight(getResources().getDisplayMetrics().heightPixels / 2)
                .setSelectedUri(selectedUri)
                .create();

        tedBottomPicker.show(getFragmentManager()); //show picker

    }


}



