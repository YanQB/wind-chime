package chime.wind.rankraise.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.message.MessageEvent;
import chime.wind.rankraise.network.vo.CategoryVo;

/**
 * Created by truekey on 18/3/2.
 */

public class EventBusActivity extends BaseActivity {
    @Bind(R.id.send_message)
    Button send_message;
    @Bind(R.id.send_message2)
    Button send_message2;

    @Override
    protected int setContentView() {
        return R.layout.activity_eventbs;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent("发送了一个信息！");
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
        send_message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryVo vo = new CategoryVo("你好啊");
                EventBus.getDefault().post(vo);
                finish();
            }
        });
    }
}
