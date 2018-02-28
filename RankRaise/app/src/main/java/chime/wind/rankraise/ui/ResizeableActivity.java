package chime.wind.rankraise.ui;

import android.os.Bundle;

import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;

/**
 * 长屏适配
 * Created by truekey on 17/12/13.
 */

public class ResizeableActivity extends BaseActivity{
    /**
     * 全面屏，特长纵横比（16:9 以上）和圆角的设计,更大纵横比的屏幕可以带给用户强烈的沉浸感，
     * 但没有经过优化的应用也就会更容易被用户感知到，这甚至会带来一些负面的体验
     * 1.在布局上留下大概 16dp 的边缘空间(响应式 UI)
     * 2.声明最大支持纵横比,兼容模式会将应用边缘的显示空间以填充
     * 针对 API level 26 或以上: 利用 android:maxAspectRatio 属性。
     * 针对 API level 25 或以下: 利用 android.max_aspect meta-data。
     * 需要注意的一点是最大支持纵横比的值只对不支持 resizableActivity 的 Activity 才有效。
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_resizeanle;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {
        //Android 7.0 以上的版本更为开发者提供了多窗口的标准支持
        //当加载 Activity 时，可以利用新的
        //FLAG_ACTIVITY_LAUNCH_ADJACENT Intent Flag 来告知系统提供多窗口支持
        //在 API level 24 以上，利用 manifest 的 android:resizeableActivity="false" 属性来禁用多窗口模式
    }
}
