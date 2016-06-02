package com.github.xujianhua.xnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.xujianhua.xnet.annotation.FileAnnotation;
import com.github.xujianhua.xnet.network.Xnet;
import com.github.xujianhua.xnet.network.listener.INetworkListener;
import com.github.xujianhua.xnet.util.FileUtils;
import com.github.xujianhua.xnet.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Xnet.getInstance();
        Test t=Xnet.create(Test.class);

//        String filename="平凡的世界.txt";
//        File file= FileUtils.createInstorageFile(this,filename);
//        try {
//            FileOutputStream outputStream=openFileOutput(filename,MODE_PRIVATE);
//            outputStream.write("平凡的世界,路遥".getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.zhaozilong);
        LogUtil.d("NetWork",""+bitmap.getByteCount());
        t.httpPostBitmap("xu01", 27, true,bitmap, new INetworkListener() {
            @Override
            public void start() {

            }

            @Override
            public void success(int code, byte[] content) {
                ImageView tv=(ImageView) findViewById(R.id.content);
                Bitmap bitmap=BitmapFactory.decodeByteArray(content,0,content.length);
                tv.setImageBitmap(bitmap);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
}
