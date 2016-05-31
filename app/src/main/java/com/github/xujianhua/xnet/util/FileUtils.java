package com.github.xujianhua.xnet.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by xujianhua on 2016/5/31.
 */
public class FileUtils {
    private static final String DIR=Environment.DIRECTORY_DOCUMENTS;
    public static boolean canMount(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static File createMusicFile(String fileName){
        return createFile(Environment.DIRECTORY_MUSIC,fileName);
    }
    private static File createFile(String dirStr,String fileName){
        if(canMount()){
            File dir=new File(dirStr);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file=new File(dir,fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                    return file;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static File createInstorageFile(Context context,String fileName){
        File file=new File(context.getFilesDir(),fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
