package com.example.xmpp;

import android.content.Context;
import android.util.Log;

import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.VCard;
import org.json.JSONObject;

import java.util.List;

import model.ChatItem;
import model.Room;

/**
 * 创建日期：2018/6/25
 * 作者： yy
 */

public class XMPP {
    public static Context ctx;

    public static void loginAccount(final String userName, final String password, XmppLoadThread.OnTaskListener xmppLoad) {

        new XmppLoadThread(ctx, xmppLoad){

            @Override
            protected Object load() {
                String url = ConfigInfo.getReqHost(ctx);
                Constants.XMPP_HOST = url.split("//")[1].split("/")[0].split(":")[0];
                Constants.XMPP_HOSTNAME = Constants.XMPP_HOST;

                Log.e("loginAccount====", userName+"===");

                boolean isSuccess = XmppConnection.getInstance(ctx).login(userName, password);
                if (isSuccess) {
                    Constants.XMPP_USERNAME = userName;
                    Constants.XMPP_PASSWORD = password;
                    Constants.XMPP_NICKNAME = MessageConstants.getNameById(ctx, userName);
                }
                return isSuccess;
            }
        };
    }

    public static void getUserInfo(String user){
        XmppConnection.getInstance(ctx).getUserInfo(user);
    }

    public static void deleteOfflineMsg(){
        XmppConnection.getInstance(ctx).deleteOfflineMsg();
    }

    public static void closeConnection(){
        XmppConnection.getInstance(ctx).closeConnection();
    }

    public static void reconnect(){
        XmppConnection.getInstance(ctx).reconnect();
    }

    public static void clearData(){
        XmppConnection.getInstance(ctx).clearData();
    }

    public static void closeXmppService(){
        XmppConnection.getInstance(ctx).closeXmppService();
    }

    public static void setNotifyClass(Class notifyClass){
        XmppConnection.getInstance(ctx).setNotifyClass(notifyClass);
    }

    public static void setRecevier(String chatName, int chatType){
        XmppConnection.getInstance(ctx).setRecevier(chatName, chatType);
    }

    public static void sendMsg(String msg, int chatType){
        try{
            JSONObject obj = new JSONObject();
            obj.put("msg", msg);
            obj.put("bodyType", "0");
            XmppConnection.getInstance(ctx).sendMsg(ctx, obj.toString(), chatType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchMember(String key){
        XmppConnection.getInstance(ctx).searchMember(key);
    }

    public static boolean addUser(String id, String userName){
        return XmppConnection.getInstance(ctx).addUser(id, userName);
    }

    public static void removeUser(String userName){
        XmppConnection.getInstance(ctx).removeUser(userName);
    }

    public static boolean changeVcard(VCard vcard){
        return XmppConnection.getInstance(ctx).changeVcard(vcard);
    }

    public static void getUservcard(String user){
        XmppConnection.getInstance(ctx).getUservcard(user);
    }



    public static MultiUserChat createRoom(String roomName, String description){
        return XmppConnection.getInstance(ctx).createRoom(roomName, description);
    }

    public static List<Room> getMyRoom(){
        return XmppConnection.getInstance(ctx).getMyRoom();
    }

    public static void getUserinfo(){
        XmppConnection.getInstance(ctx).getUserinfo();
    }

}
