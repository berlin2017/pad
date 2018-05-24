package com.berlin.testpad.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.history.HistoryHomeActivity;
import com.berlin.testpad.socre.ScoreActivity;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private Map<Integer, Fragment> mFragments = new TreeMap<Integer, Fragment>();
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
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
                    fragment = new MainFragment();
                    break;
                case R.id.main_radiobutton2:
                    fragment = new MainFragment2();
                    break;
                case R.id.main_radiobutton3:
                    fragment = new MainFragment3();
                    break;
                case R.id.main_radiobutton4:
                    fragment = new MainFragment4();
                    break;
                case R.id.main_radiobutton5:
                    fragment = new MainFragment5();
                    break;
                case R.id.main_radiobutton6:
                    fragment = new MainFragment6();
                    break;
                case R.id.main_radiobutton7:
                    fragment = new MainFragment7();
                    break;
                case R.id.main_radiobutton8:
                    fragment = new MainFragment8();
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

    public void toChoose(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public void toHistory(View view) {
        Intent intent = new Intent(this, HistoryHomeActivity.class);
        startActivity(intent);
    }
}
