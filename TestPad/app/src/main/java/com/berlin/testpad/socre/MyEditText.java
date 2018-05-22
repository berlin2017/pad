package com.berlin.testpad.socre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;

/**
 * Created by ahxmt on 2018/5/22.
 */

@SuppressLint("AppCompatCustomView")
public class MyEditText extends EditText {

    private int minValue = 0;
    private int maxValue = 10;

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocvus) {
                ViewParent parent = v.getParent().getParent();
                if (parent instanceof TextInputLayout) {
                    ((TextInputLayout)parent).setError(null);
                    if (!hasFocvus){
                        if (TextUtils.isEmpty(getText().toString())){
                            ((TextInputLayout)parent).setError("不能为空");
                            return;
                        }
                    }
                    if (Integer.parseInt(getText().toString())<minValue){
                        ((TextInputLayout)parent).setError("不能小于"+minValue);
                    }
                    if (Integer.parseInt(getText().toString())<maxValue){
                        ((TextInputLayout)parent).setError("不能大于"+maxValue);
                    }
                }
            }
        });
    }

}
