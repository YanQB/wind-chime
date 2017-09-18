package chime.wind.rankraise.ui;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;

/**
 * Created by truekey on 17/9/8.
 */

public class ThreadLoopActivity extends BaseActivity {
    @Bind(R.id.start)
    TextView start;

    @Override
    protected int setContentView() {
        return R.layout.activity_thread_loop;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {
        postThread();
    }

    private void postThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.getUncaughtExceptionHandler();
    }

}
