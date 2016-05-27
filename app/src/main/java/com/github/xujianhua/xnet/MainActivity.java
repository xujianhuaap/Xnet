package com.github.xujianhua.xnet;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.widget.TextView;

import com.github.xujianhua.xnet.network.Xnet;
import com.github.xujianhua.xnet.network.listener.INetworkListener;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Xnet.getInstance();
        Test t=Xnet.create(Test.class);
        t.httpPost("xu01", 27, true, new INetworkListener() {
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
