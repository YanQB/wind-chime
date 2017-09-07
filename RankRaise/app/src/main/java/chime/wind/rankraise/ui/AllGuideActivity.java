package chime.wind.rankraise.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.base.list.CommonListAdapter;
import chime.wind.rankraise.base.list.CommonListViewHolder;

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

            }
        };
        listView.setAdapter(commonListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
