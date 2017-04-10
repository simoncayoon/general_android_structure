package com.beetron.projname_appshorthand.ui.common;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.beetron.projname_appshorthand.R;
import com.beetron.projname_appshorthand.util.BaseAppManager;


/**
 * Created by DKY with IntelliJ IDEA.
 * Author: DKY email: losemanshoe@gmail.com.
 * Date: 2016/8/18.
 * Time: 10:57.
 */
public abstract  class BaseAppCompatActivity extends AppCompatActivity {

    protected static String TAG_LOG = null;
    protected Context mContext = null;
    public static final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4, SCALE = 5, FADE = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        switch (getOverridePendingTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
//            case RIGHT:
//                overridePendingTransition(R.anim.right_in, R.anim.right_out);
//                break;
//            case TOP:
//                overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                break;
//            case BOTTOM:
//                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                break;
//            case SCALE:
//                overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                break;
            case FADE:
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            default:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
        }
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        mContext = this;
        TAG_LOG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);
        initViewsAndEvents();
    }


    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
        switch (getOverridePendingTransitionMode()) {
            case LEFT:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
//            case RIGHT:
//                overridePendingTransition(R.anim.right_in, R.anim.right_out);
//                break;
//            case TOP:
//                overridePendingTransition(R.anim.top_in, R.anim.top_out);
//                break;
//            case BOTTOM:
//                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
//                break;
//            case SCALE:
//                overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
//                break;
            case FADE:
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            default:
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // 当新设置中，屏幕布局模式为横排时
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //TODO 某些操作
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DetoryViewAndThing();
    }

//    protected abstract int getContentViewLayoutID();
    protected abstract void getBundleExtras(Bundle extras);
    protected abstract void initViewsAndEvents();
    protected abstract int getOverridePendingTransitionMode();
    protected abstract void DetoryViewAndThing();
}
