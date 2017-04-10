package com.beetron.projname_appshorthand.ui.common;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beetron.projname_appshorthand.R;
import com.beetron.projname_appshorthand.customview.CustomToast;


/**
 * Created by DKY with IntelliJ IDEA.
 * Author: DKY email: losemanshoe@gmail.com.
 * Date: 2016/6/23.
 * Time: 15:15.
 */
public abstract class BaseActivity extends BaseAppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private LinearLayout rootLayout;
    private Toolbar mToolbar;
    private TextView mToolbarTitle;

    protected abstract int getLayoutId();
    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        setContentView(getLayoutId());
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initViewsAndEvents();
    }

    @Override
    public void setContentView(int layoutId) {
        if (layoutId == 0){
            return;
        }
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void initViewsAndEvents() {
        initToolbar();
        initView();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getOverridePendingTransitionMode() {
        return LEFT;
    }

    @Override
    protected void DetoryViewAndThing() {

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if(null != getToolbar() && isShowBacking()){
            setSubActivityEnable();
        }
    }

    /**
     * 获取头部标题的TextView
     * @return
     */
    protected TextView getToolbarTitle(){
        return mToolbarTitle;
    }

    /**
     * 设置头部标题
     * @param title
     */
    protected void setToolBarTitle(CharSequence title) {
        if(mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else{
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    protected void setSubActivityEnable() {
        if (mToolbar == null) {
            return ;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getToolbar().setNavigationIcon(R.drawable.arrow_left_back_selector);//返回按钮
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking(){
        return true;
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 跳转
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void toastShow(String promptText){
        CustomToast.showToast(this, promptText);
    }
}
