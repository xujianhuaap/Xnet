package com.github.xujianhua.xnet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.xujianhua.xnet.annotation.FileAnnotation;
import com.github.xujianhua.xnet.network.Xnet;
import com.github.xujianhua.xnet.network.listener.INetworkListener;
import com.github.xujianhua.xnet.util.FileUtils;

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

        String filename="平凡的世界.txt";
        File file= FileUtils.createInstorageFile(this,filename);
        try {
            FileOutputStream outputStream=openFileOutput(filename,MODE_PRIVATE);
            outputStream.write("平凡的世界,路遥".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        t.httpPostBitmap("xu01", 27, true,file, new INetworkListener() {
            @Override
            public void start() {

            }

            @Override
            public void success(int code, byte[] content) {
                TextView tv=(TextView)findViewById(R.id.content);
                try {
                    tv.setText(new String(content,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
}
