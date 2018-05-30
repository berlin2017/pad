package com.berlin.testpad;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.berlin.testpad.main.MainActivity;
import com.berlin.testpad.socre.MyTask;
import com.berlin.testpad.socre.ScoreActivity;
import com.thl.filechooser.FileChooser;
import com.thl.filechooser.FileInfo;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class BaseFragment extends Fragment{

    private AlertDialog alertDialog;
    private AlertDialog name_dialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showNameDialog();
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
        if ( name_dialog!=null && name_dialog.isShowing() ){
            name_dialog.dismiss();
        }
    }

    public void showNameDialog(){
        if (name_dialog!=null){
            name_dialog.show();
            return;
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog,null);
        Button button = view.findViewById(R.id.dialog_choose);
        final TextView textView = view.findViewById(R.id.dialog_path);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser fileChooser = new FileChooser(getActivity(), new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        textView.setText(filePath);
                    }
                });

                fileChooser.setBackIconRes(R.drawable.ic_back_png);
                fileChooser.setTitle("选择文件路径");
                fileChooser.setDoneText("确定");
                fileChooser.setThemeColor(R.color.colorAccent);

                fileChooser.setChooseType(FileInfo.FILE_TYPE_FOLDER);
                fileChooser.showFile(false);  //是否显示文件
                fileChooser.open();
            }
        });
        name_dialog = new AlertDialog.Builder(getActivity()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //确定
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消
            }
        }).setTitle("是否存为文件？").setView(view).create();
        name_dialog.show();


    }

    public void dismissNameDialog() {
        if (null != name_dialog && name_dialog.isShowing()) {
            name_dialog.dismiss();
        }
    }
}
