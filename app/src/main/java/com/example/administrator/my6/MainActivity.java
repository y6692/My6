package com.example.administrator.my6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xmpp.Constants;
import com.example.xmpp.XMPP;
import com.example.xmpp.XmppConnection;
import com.example.xmpp.XmppLoadThread;

import org.json.JSONObject;
import model.ChatItem;



public class MainActivity extends AppCompatActivity {
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        XMPP.ctx = ctx;

        XmppLoadThread.OnTaskListener xmppLoad = new XmppLoadThread.OnTaskListener() {
            @Override
            public void onSuccess() {
                Log.e("suc====0001", "====");

                XMPP.setNotifyClass(MainActivity.class);
                XMPP.setRecevier("yy",  ChatItem.CHAT);
                XMPP.sendTextMsg( "123",  ChatItem.CHAT);

//                String a = Constants.XMPP_HOSTNAME;
//                XmppConnection.getInstance(ctx).createRoom(ctx, "", "");
            }

            @Override
            public void onFailed() {
                Log.e("fail====", "====");
            }
        };

        XMPP.loginAccount("13382856367", "e10adc3949ba59abbe56e057f20f883e", xmppLoad);

    }
}
