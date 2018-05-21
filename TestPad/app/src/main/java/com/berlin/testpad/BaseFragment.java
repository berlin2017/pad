package com.berlin.testpad;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class BaseFragment extends Fragment{

    private AlertDialog alertDialog;

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
