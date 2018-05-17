package com.berlin.testpad.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.berlin.testpad.R;

public class MainFragment3 extends Fragment {

    public static MainFragment3 mainFragment;

    public static MainFragment3 newInstance(){
        if (mainFragment==null)
            mainFragment = new MainFragment3();
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_table3,container,false);
        return view;
    }


}
