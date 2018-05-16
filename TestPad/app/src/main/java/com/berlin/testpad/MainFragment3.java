package com.berlin.testpad;

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

public class MainFragment3 extends Fragment {

    public static MainFragment3 mainFragment;
    private LinearLayout relativeLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout6;
    private LinearLayout linearLayout7;
    private LinearLayout linearLayout8;
    private LinearLayout linearLayout9;
    private LinearLayout linearLayout10;
    private LinearLayout linearLayout11;
    private LinearLayout linearLayout12;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;

    public static MainFragment3 newInstance(){
        if (mainFragment==null)
            mainFragment = new MainFragment3();
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment3,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        relativeLayout1 = view.findViewById(R.id.main_fragment_rightpart1_1);
        linearLayout2 = view.findViewById(R.id.main_fragment_rightpart2_1);
        linearLayout3 = view.findViewById(R.id.main_fragment_rightpart3_1);
        linearLayout4 = view.findViewById(R.id.main_fragment_rightpart4_1);
        linearLayout5 = view.findViewById(R.id.main_fragment_rightpart5_1);
        linearLayout6 = view.findViewById(R.id.main_fragment_rightpart6_1);
        linearLayout7 = view.findViewById(R.id.main_fragment_rightpart7_1);
        linearLayout8 = view.findViewById(R.id.main_fragment_rightpart8_1);
        linearLayout9 = view.findViewById(R.id.main_fragment_rightpart9_1);
        linearLayout10 = view.findViewById(R.id.main_fragment_rightpart10_1);
        linearLayout11 = view.findViewById(R.id.main_fragment_rightpart11_1);
        linearLayout12 = view.findViewById(R.id.main_fragment_rightpart12_1);
        relativeLayout2 = view.findViewById(R.id.main_fragment1);
        relativeLayout3 = view.findViewById(R.id.main_relative1);
        textView1 = view.findViewById(R.id.main_fragment_text1);
        textView2 = view.findViewById(R.id.main_fragment_text2);
        textView3 = view.findViewById(R.id.main_fragment_text4);
        textView4 = view.findViewById(R.id.main_fragment_text5);
        textView5 = view.findViewById(R.id.main_fragment_text6);
        textView6 = view.findViewById(R.id.main_fragment_text7);
        textView7 = view.findViewById(R.id.main_fragment_text8);
        textView8 = view.findViewById(R.id.main_fragment_text9);
        textView9 = view.findViewById(R.id.main_fragment_text10);
        textView10 = view.findViewById(R.id.main_fragment_text11);
        textView11 = view.findViewById(R.id.main_fragment_text12);
        textView12 = view.findViewById(R.id.main_fragment_text13);
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height =view.getMeasuredHeight();
                int width =view.getMeasuredWidth();
                Log.e("height0",height+"");
                Log.e("width0",width+"");
                ViewGroup.LayoutParams params = relativeLayout1.getLayoutParams();
                params.width = width/9*4;
                relativeLayout1.setLayoutParams(params);



                ViewGroup.LayoutParams params2 = relativeLayout2.getLayoutParams();
                params2.width = width/9*7;
                relativeLayout2.setLayoutParams(params2);

                ViewGroup.LayoutParams params3 = textView1.getLayoutParams();
                params3.width = width/9*3;
                textView1.setLayoutParams(params3);

                ViewGroup.LayoutParams textView2_params = textView2.getLayoutParams();
                textView2_params.width = width/9*3;
                textView2.setLayoutParams(textView2_params);

                ViewGroup.LayoutParams textView3_params = textView3.getLayoutParams();
                textView3_params.width = width/9*3;
                textView3.setLayoutParams(textView3_params);

                ViewGroup.LayoutParams textView4_params = textView4.getLayoutParams();
                textView4_params.width = width/9*3;
                textView4.setLayoutParams(textView4_params);

                ViewGroup.LayoutParams textView5_params = textView5.getLayoutParams();
                textView5_params.width = width/9*3;
                textView5.setLayoutParams(textView5_params);

                ViewGroup.LayoutParams textView6_params = textView6.getLayoutParams();
                textView6_params.width = width/9*3;
                textView6.setLayoutParams(textView6_params);

                ViewGroup.LayoutParams textView7_params = textView7.getLayoutParams();
                textView7_params.width = width/9*3;
                textView7.setLayoutParams(textView7_params);

                ViewGroup.LayoutParams textView8_params = textView8.getLayoutParams();
                textView8_params.width = width/9*3;
                textView8.setLayoutParams(textView8_params);

                ViewGroup.LayoutParams textView9_params = textView9.getLayoutParams();
                textView9_params.width = width/9*3;
                textView9.setLayoutParams(textView9_params);

                ViewGroup.LayoutParams textView10_params = textView10.getLayoutParams();
                textView10_params.width = width/9*3;
                textView10.setLayoutParams(textView10_params);

                ViewGroup.LayoutParams textView11_params = textView11.getLayoutParams();
                textView11_params.width = width/9*3;
                textView11.setLayoutParams(textView11_params);

                ViewGroup.LayoutParams textView12_params = textView12.getLayoutParams();
                textView12_params.width = width/9*3;
                textView12.setLayoutParams(textView12_params);

                ViewGroup.LayoutParams params5 = relativeLayout3.getLayoutParams();
                params5.width = width/9*8;
                relativeLayout3.setLayoutParams(params5);

                //-----------------
                ViewGroup.LayoutParams params6 = linearLayout2.getLayoutParams();
                params6.width = width/9*4;
                linearLayout2.setLayoutParams(params6);

                ViewGroup.LayoutParams linearLayout3_params = linearLayout3.getLayoutParams();
                linearLayout3_params.width = width/9*4;
                linearLayout3.setLayoutParams(linearLayout3_params);

                ViewGroup.LayoutParams linearLayout4_params = linearLayout4.getLayoutParams();
                linearLayout4_params.width = width/9*4;
                linearLayout4.setLayoutParams(linearLayout4_params);

                ViewGroup.LayoutParams linearLayout5_params = linearLayout5.getLayoutParams();
                linearLayout5_params.width = width/9*4;
                linearLayout5.setLayoutParams(linearLayout5_params);

                ViewGroup.LayoutParams linearLayout6_params = linearLayout6.getLayoutParams();
                linearLayout6_params.width = width/9*4;
                linearLayout6.setLayoutParams(linearLayout6_params);

                ViewGroup.LayoutParams linearLayout7_params = linearLayout7.getLayoutParams();
                linearLayout7_params.width = width/9*4;
                linearLayout7.setLayoutParams(linearLayout7_params);

                ViewGroup.LayoutParams linearLayout8_params = linearLayout8.getLayoutParams();
                linearLayout8_params.width = width/9*4;
                linearLayout8.setLayoutParams(linearLayout8_params);

                ViewGroup.LayoutParams linearLayout9_params = linearLayout9.getLayoutParams();
                linearLayout9_params.width = width/9*4;
                linearLayout9.setLayoutParams(linearLayout9_params);

                ViewGroup.LayoutParams linearLayout10_params = linearLayout10.getLayoutParams();
                linearLayout10_params.width = width/9*4;
                linearLayout10.setLayoutParams(linearLayout10_params);

                ViewGroup.LayoutParams linearLayout11_params = linearLayout11.getLayoutParams();
                linearLayout11_params.width = width/9*4;
                linearLayout11.setLayoutParams(linearLayout11_params);

                ViewGroup.LayoutParams linearLayout12_params = linearLayout12.getLayoutParams();
                linearLayout12_params.width = width/9*4;
                linearLayout12.setLayoutParams(linearLayout12_params);

                ViewGroup.LayoutParams params7 = relativeLayout2.getLayoutParams();
                params7.width = width/9*7;
                relativeLayout2.setLayoutParams(params7);
            }
        });
    }
}
