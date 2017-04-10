package com.beetron.projname_appshorthand.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TempDataManager {

    public static final String TAG = TempDataManager.class.getSimpleName();
    private static final String SP_GENERAL_PROFILE_NAME = "tlf_temp_data";
    private static final String CURRENT_NAME = "CURRENT_NAME";
    private static final String CURRENT_USER_PWD = "CURRENT_USER_PWD";
    private static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    private static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    private static final String SEARCH_BASIS_HISTORY = "SEARCH_BASIS_HISTORY";

    private static TempDataManager instance = null;
    private Context mContext = null;
    private SharedPreferences sp = null;
    private Editor mEditor = null;

    private TempDataManager(Context context) {
        mContext = context;
        sp = mContext.getSharedPreferences(SP_GENERAL_PROFILE_NAME,
                Context.MODE_PRIVATE);
        mEditor = sp.edit();
    }

    public static TempDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new TempDataManager(context);
        }
        return instance;
    }

    /**
     * 获取最近一次用户的登录名
     * @return
     */
    public String getLastUser() {
        return sp.getString(CURRENT_NAME, "");
    }

    /**
     * 清空当前临时信息
     */
    public void clearCurrentTemp() {
        mEditor.clear();
        mEditor.commit();
    }

    public void saveUserInfo(String userName, String pwd, Long userId) {
        mEditor.putString(CURRENT_NAME, userName);
        mEditor.putString(CURRENT_USER_PWD, pwd);
        mEditor.putLong(CURRENT_USER_ID, userId);
        mEditor.commit();
    }

    public String getLastUserPwd() {
        return sp.getString(CURRENT_USER_PWD, "");
    }

    public Long getUserId() {
        return sp.getLong(CURRENT_USER_ID, -1);
    }

    public String getHistoryStr() {
        return sp.getString(SEARCH_HISTORY, "");
    }

    public void saveSearchHis(String s) {
        mEditor.putString(SEARCH_HISTORY, s).commit();
    }

    public void saveSearchBasisHis(String s) {
        mEditor.putString(SEARCH_BASIS_HISTORY, s).commit();
    }

    public String getBasisStr() {
        return sp.getString(SEARCH_BASIS_HISTORY, "");
    }

    public void clearPubBasisHistory() {
        mEditor.remove(SEARCH_BASIS_HISTORY);
        mEditor.commit();
    }

    public void clearCaseSearch() {
        mEditor.remove(SEARCH_HISTORY);
        mEditor.commit();
    }
}
