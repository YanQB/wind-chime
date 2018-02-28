package chime.wind.rankraise.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Random;

import butterknife.Bind;
import chime.wind.rankraise.R;
import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.binder.CategoryViewBinder;
import chime.wind.rankraise.network.vo.CategoryVo;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static me.drakeet.multitype.MultiTypeAsserts.assertAllRegistered;
import static me.drakeet.multitype.MultiTypeAsserts.assertHasTheSameAdapter;

/**
 * Created by truekey on 18/2/28.
 */

public class MultiTypeActivity extends BaseActivity {
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    @Bind(R.id.rlist)
    RecyclerView rlist;


    private MultiTypeAdapter adapter;
    /* Items 等同于 ArrayList<Object> */
    private Items items;

    @Override
    protected int setContentView() {
        return R.layout.activity_multi_type;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContent);
        rlist.setLayoutManager(layoutManager);

        adapter = new MultiTypeAdapter();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(mContent, "刷新了", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        refresh.setRefreshing(false);
                    }
                }, 1200);
            }
        });


        /* 注册类型和 View 的对应关系 */
        adapter.register(CategoryVo.class, new CategoryViewBinder());

        /* 模拟加载数据，也可以稍后再加载，然后使用
         * adapter.notifyDataSetChanged() 刷新列表 */
        items = new Items();
        for (int i = 0; i < 20; i++) {
            items.add(new CategoryVo("测试数据" + i));
        }
        adapter.setItems(items);

          /* 断言所有使用的类型都已注册 */
        assertAllRegistered(adapter, items);
        rlist.setAdapter(adapter);
        /* 断言 recyclerView 使用的是正确的 adapter */
        assertHasTheSameAdapter(rlist, adapter);

        //adapter.notifyDataSetChanged();


    }
}
