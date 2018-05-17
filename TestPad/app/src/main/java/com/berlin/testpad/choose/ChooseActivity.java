package com.berlin.testpad.choose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.berlin.testpad.BaseActivity;
import com.berlin.testpad.R;
import com.berlin.testpad.socre.ScoreActivity;

/**
 * Created by ahxmt on 2018/5/17.
 */

public class ChooseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_choose);
    }

    public void back(View view){
        finish();
    }

    public void toScore(View view){
        String tag = (String) view.getTag();
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }
}
