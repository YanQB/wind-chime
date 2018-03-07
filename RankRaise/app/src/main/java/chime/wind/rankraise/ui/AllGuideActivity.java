package chime.wind.rankraise.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.base.list.CommonListAdapter;
import chime.wind.rankraise.base.list.CommonListViewHolder;
import chime.wind.rankraise.message.MessageEvent;
import chime.wind.rankraise.network.vo.CategoryVo;

/**
 * 引导界面
 * Created by truekey on 17/9/7.
 */

public class AllGuideActivity extends BaseActivity {

    @Bind(R.id.mlist)
    ListView listView;

    CommonListAdapter<String> commonListAdapter;
    List<String> datalist = new LinkedList();

    @Override
    protected int setContentView() {
        return R.layout.activity_list;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {
        String[] strings;
        strings = getResources().getStringArray(R.array.basic_tool);
        datalist = Arrays.asList(strings);

        commonListAdapter = new CommonListAdapter<String>(mContent, R.layout.itme_all_guide, datalist) {
            @Override
            protected void fillItemData(CommonListViewHolder viewHolder, int position, String item) {
                viewHolder.setTextForTextView(R.id.text, item);
            }
        };
        listView.setAdapter(commonListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) adapterView.getAdapter().getItem(i);
                switch (str) {
                    case "StringTool":
                        break;
                    case "自定义键盘":
                        push(mContent, MkeyBoarActivity.class);
                        break;
                    case "MultiType":
                        push(mContent,MultiTypeActivity.class);
                        break;
                    case "EvenBus":
                        push(mContent,EventBusActivity.class);
                        break;
                }
            }
        });

        //EventBus注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus解除事件
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(Object o){
        if(o instanceof MessageEvent){
            MessageEvent messageEvent = (MessageEvent)o;
            showToast(messageEvent.getMessage());
        }else if(o instanceof CategoryVo){
            CategoryVo messageEvent = (CategoryVo)o;
            showToast(messageEvent.getText());
        }
    }
}
