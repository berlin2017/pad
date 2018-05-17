package com.berlin.testpad.socre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.main.MainFragment;
import com.berlin.testpad.main.MainFragment2;
import com.berlin.testpad.main.MainFragment3;
import com.berlin.testpad.main.MainFragment4;
import com.berlin.testpad.main.MainFragment5;
import com.berlin.testpad.main.MainFragment6;
import com.berlin.testpad.main.MainFragment7;
import com.berlin.testpad.main.MainFragment8;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ahxmt on 2018/5/17.
 */

public class ScoreActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private Map<Integer, Fragment> mFragments = new TreeMap<Integer, Fragment>();
    private Fragment mCurrentFragment;

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
        radioGroup.check(R.id.main_radiobutton1);
    }


    private void changeFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = mFragments.get(id);
        if (fragment == null) {
            switch (id) {
                case R.id.main_radiobutton1:
                    fragment = MainFragment.newInstance();
                    break;
                case R.id.main_radiobutton2:
                    fragment = MainFragment2.newInstance();
                    break;
                case R.id.main_radiobutton3:
                    fragment = MainFragment3.newInstance();
                    break;
                case R.id.main_radiobutton4:
                    fragment = MainFragment4.newInstance();
                    break;
                case R.id.main_radiobutton5:
                    fragment = MainFragment5.newInstance();
                    break;
                case R.id.main_radiobutton6:
                    fragment = MainFragment6.newInstance();
                    break;
                case R.id.main_radiobutton7:
                    fragment = MainFragment7.newInstance();
                    break;
                case R.id.main_radiobutton8:
                    fragment = MainFragment8.newInstance();
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

    }

    public void buildCode(View view){

    }
}
