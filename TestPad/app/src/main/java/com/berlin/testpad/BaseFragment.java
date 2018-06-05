package com.berlin.testpad;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berlin.testpad.socre.ExcelUtils;
import com.berlin.testpad.socre.model.ScoreModel;
import com.berlin.testpad.utis.MyUtils;
import com.thl.filechooser.FileChooser;
import com.thl.filechooser.FileInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class BaseFragment extends Fragment{

    private AlertDialog alertDialog;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    public void showLoadingDialog() {
        if (alertDialog!=null){
            alertDialog.show();
            return;
        }
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_dialog);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( alertDialog!=null && alertDialog.isShowing() ){
            alertDialog.dismiss();
        }

    }


}
