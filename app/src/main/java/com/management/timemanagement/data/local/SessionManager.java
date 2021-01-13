package com.management.timemanagement.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    SharedPreferences pref;

    Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "login";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String NICKNAME_PREF = "nickname";

    private static final String IS_MODERATOR = "isModer";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        editor.commit();
    }

    public void setNickname(String nick) {

        editor.putString(NICKNAME_PREF, nick);

        editor.commit();
    }

    public void setModer(boolean is) {
        editor.putBoolean(IS_MODERATOR, is);

        editor.commit();
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getLogin(){
        return pref.getString(NICKNAME_PREF, "");
    }

    public boolean isModerator() {
        return pref.getBoolean(IS_MODERATOR, false);
    }
}
