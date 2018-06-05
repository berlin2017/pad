package com.berlin.testpad;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class BaseActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private AlertDialog name_dialog;
    private OnSaveFileInterface onSaveFileInterface;

    public void setOnFileSaveInterface(OnSaveFileInterface onSaveFileInterface){
        this.onSaveFileInterface = onSaveFileInterface;
    }

    public void showLoadingDialog() {
        if (alertDialog!=null){
            alertDialog.show();
            return;
        }
        alertDialog = new AlertDialog.Builder(this).create();
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    public void showNameDialog(final ScoreModel scoreModel){
        if (name_dialog!=null){
            name_dialog.show();
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog,null);
        final EditText editText = view.findViewById(R.id.dialog_name_edit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        editText.setText( simpleDateFormat.format(new Date()));
        Button button = view.findViewById(R.id.dialog_choose);
        final TextView textView = view.findViewById(R.id.dialog_path);
        textView.setText(MyUtils.getCacheFile(this, ExcelUtils.DIR_FILE_NAME).getAbsolutePath());
        final Activity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser fileChooser = new FileChooser(activity, new FileChooser.FileChoosenListener() {
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
        name_dialog = new AlertDialog.Builder(this).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //确定
                if(TextUtils.isEmpty(editText.getText().toString())){
                    Toast.makeText(getApplicationContext(),"文件名不能为空",Toast.LENGTH_SHORT).show();
                    showNameDialog(scoreModel);
                    return;
                }
                if(onSaveFileInterface!=null){
                    onSaveFileInterface.onConfirm(scoreModel,textView.getText().toString(),editText.getText().toString());
                }
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

    public interface OnSaveFileInterface{
        void onConfirm(ScoreModel scoreModel,String path,String name);
    }

}
