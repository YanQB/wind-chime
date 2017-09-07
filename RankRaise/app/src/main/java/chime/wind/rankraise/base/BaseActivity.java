package chime.wind.rankraise.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;


import java.util.Locale;

import butterknife.ButterKnife;


/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContent;
    protected Bundle bundle = null;
    public static final String KEY_BUNDLE_FLAGS = "key_bundle_flags";
    public static final String KEY_JSON = "key_json";
    public static final String KEY_PARCELABLE = "key_parcelable";

    protected String key_bundle_flags;
    protected String key_json;
    protected Parcelable key_valueBundle;

    protected int mWidth;
    protected int mheight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = this;
        setLanguage();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setContentView());
        //注解第二步
        ButterKnife.bind(this);
        initBundle();
        onAfterCreate(savedInstanceState);
        WindowManager wm = (WindowManager) mContent.getSystemService(Context.WINDOW_SERVICE);


        mWidth = wm.getDefaultDisplay().getWidth();
        mheight = wm.getDefaultDisplay().getHeight();
    }

    protected abstract int setContentView();

    protected abstract void onAfterCreate(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initBundle() {
        bundle = this.getIntent().getExtras();
        if (null != bundle) {
            key_bundle_flags = bundle.getString(KEY_BUNDLE_FLAGS, "");
            if (!TextUtils.isEmpty(key_bundle_flags)) {
                key_json = bundle.getString(KEY_JSON);
                key_valueBundle = bundle.getParcelable(KEY_PARCELABLE);
            }
        }
    }


    public String getSimpleName() {
        return ((Object) this).getClass().getSimpleName();
    }


    // push

    public static void push(Context context, Class<?> cls) {
        push(context, cls, null, null, null, 0, null);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, Bundle bundle) {
        push(context, cls, sourceTag, null, null, 0, bundle);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, String json) {
        push(context, cls, sourceTag, json, null, 0);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, String json, int flags) {
        push(context, cls, sourceTag, json, null, flags);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, Parcelable value) {
        push(context, cls, sourceTag, null, value, 0);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, Parcelable value, int flags) {
        push(context, cls, sourceTag, null, value, flags);
    }


    public static void push(Context context, Class<?> cls, String sourceTag, String json, Parcelable value, int flags) {
        push(context, cls, sourceTag, json, value, flags, null);
    }

    public static void push(Context context, Class<?> cls, String sourceTag, String json, Parcelable value, int flags, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle == null)
            bundle = new Bundle();
        bundle.putString(KEY_BUNDLE_FLAGS, sourceTag);

        if (!TextUtils.isEmpty(json))
            bundle.putString(KEY_JSON, json);
        if (null != value)
            bundle.putParcelable(KEY_PARCELABLE, value);
        if (flags != 0)
            intent.setFlags(flags);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    public void showToast(CharSequence text) {
        Toast toast = Toast.makeText(mContent, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(int resId) {
        showToast(getResources().getString(resId));
    }


    private void setLanguage() { //读取SharedPreferences数据，默认选中第一项
        SharedPreferences preferences = getSharedPreferences("language", Context.MODE_PRIVATE);
        int language = preferences.getInt("language", 0);
        //根据读取到的数据，进行设置
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        switch (language) {
            case 0:
                configuration.locale = Locale.getDefault();
                break;
            case 1:
                configuration.locale = Locale.CHINESE;
                break;
            default:
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }


    public String setText(String text) {
        String str = "";
        if (!TextUtils.isEmpty(text)) {
            str = text;
        }
        return str;
    }

    public String getMstring(int res) {
        return mContent.getResources().getString(res);
    }

}
