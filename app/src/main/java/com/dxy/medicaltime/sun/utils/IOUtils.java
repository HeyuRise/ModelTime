package com.dxy.medicaltime.sun.utils;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
public class IOUtils {
    public static void getApkByNetWork(Context context, InputStream is, File file, long totalSize) {
        byte[] bytes = new byte[1024];
        int temp = 0;

        long total=0;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            while ((temp = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
                total+=temp;
                int percent = (int) (total*100/totalSize);
                Intent intent =new Intent();
                intent.setAction("0711");
                intent.putExtra("percent",percent);
                intent.putExtra("path",file.getAbsolutePath());
                context.sendBroadcast(intent);

            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
