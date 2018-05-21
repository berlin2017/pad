package com.berlin.testpad.socre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.main.MainFragment;
import com.berlin.testpad.main.MainFragment2;
import com.berlin.testpad.main.MainFragment3;
import com.berlin.testpad.main.MainFragment4;
import com.berlin.testpad.main.MainFragment5;
import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ahxmt on 2018/5/17.
 */

public class ScoreActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private Map<Integer, Fragment> mFragments = new TreeMap<Integer, Fragment>();
    private Fragment mCurrentFragment;
    private ImageView imageView;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_score);
        radioGroup = findViewById(R.id.main_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                changeFragment(i);
            }
        });
        imageView = findViewById(R.id.score_qcode_image);
        index = getIntent().getIntExtra("index",0);
        int id = R.id.main_radiobutton1;
        switch (index){
            case 0:
                id = R.id.main_radiobutton1;
                break;
            case 1:
                id = R.id.main_radiobutton2;
                break;
            case 2:
                id = R.id.main_radiobutton3;
                break;
            case 3:
                id = R.id.main_radiobutton4;
                break;
            case 4:
                id = R.id.main_radiobutton5;
                break;
        }
        radioGroup.check(id);
    }


    private void changeFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = mFragments.get(id);
        if (fragment == null) {
            switch (id) {
                case R.id.main_radiobutton1:
                    fragment = new ScoreFragment1();
                    fragment.setArguments(getIntent().getExtras());
                    break;
                case R.id.main_radiobutton2:
                    fragment = new ScoreFragment2();
                    fragment.setArguments(getIntent().getExtras());
                    break;
                case R.id.main_radiobutton3:
                    fragment = new ScoreFragment3();
                    fragment.setArguments(getIntent().getExtras());
                    break;
                case R.id.main_radiobutton4:
                    fragment = new ScoreFragment4();
                    fragment.setArguments(getIntent().getExtras());
                    break;
                case R.id.main_radiobutton5:
                    fragment = new ScoreFragment5();
                    fragment.setArguments(getIntent().getExtras());
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                mFragments.put(id, fragment);
            }
        }
        if (fragment == mCurrentFragment) {
            return;
        }
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (fragment != null) {
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.main_fragment_container, fragment);
            }
        }
        transaction.commitAllowingStateLoss();
        mCurrentFragment = fragment;
    }

    public void back(View view){
        finish();
    }

    public void save(View view){
        if(mCurrentFragment instanceof ScoreFragment1){
            ((ScoreFragment1)mCurrentFragment).save();
        }else if (mCurrentFragment instanceof ScoreFragment2){
            ((ScoreFragment2)mCurrentFragment).save();
        }else if(mCurrentFragment instanceof ScoreFragment3){
            ((ScoreFragment3)mCurrentFragment).save();
        }else if(mCurrentFragment instanceof ScoreFragment4){
            ((ScoreFragment4)mCurrentFragment).save();
        }else if(mCurrentFragment instanceof ScoreFragment5){
            ((ScoreFragment5)mCurrentFragment).save();
        }
    }

    public void buildCode(View view){
        if (imageView.getVisibility() == View.VISIBLE){
            imageView.setVisibility(View.GONE);
            return;
        }
        String contentEtString = "aaaaaaaaaaaaaaaaaaa";

        if (TextUtils.isEmpty(contentEtString)) {
            Toast.makeText(this, "contentEtString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = null;
        try {
            /*
             * contentEtString：字符串内容
             * w：图片的宽
             * h：图片的高
             * logo：不需要logo的话直接传null
             * */

            Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            bitmap = CodeCreator.createQRCode(contentEtString, 400, 400, logo);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
