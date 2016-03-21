package com.github.xujianhua.xnet;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;

import com.github.xujianhua.xnet.network.Xnet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Xnet.getInstance();
        Test t=Xnet.create(Test.class);
        t.httpPost("bdwz","lili",null);


    }
}
