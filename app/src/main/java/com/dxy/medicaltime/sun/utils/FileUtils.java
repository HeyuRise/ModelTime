package com.dxy.medicaltime.sun.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;


public class FileUtils {

    public static File getStorageFile(String name) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "/0711");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file1 = new File(file, name);
            return file1;

        } else {
            Log.e("====", "==外部存储路径不存在==");
        }
        return  null;
    }
}
