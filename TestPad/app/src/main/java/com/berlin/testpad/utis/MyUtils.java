package com.berlin.testpad.utis;

import android.content.Context;
import android.os.Environment;

import com.berlin.testpad.user.UserManager;

import java.io.File;

/**
 * Created by ahxmt on 2018/5/22.
 */

public class MyUtils {
    /**
     * 获得存储文件
     *
     * @param
     * @param
     * @return
     */
    public static File getCacheFile(Context context, String name) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if (context.getExternalCacheDir() == null) {
                cachePath = context.getCacheDir().getPath();
            } else {
                cachePath = context.getExternalCacheDir().getPath();
            }

        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + name + File.separator + UserManager.getUser(context).getUsername());
    }

}
