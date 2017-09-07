package chime.wind.rankraise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.ui.AllGuideActivity;
import chime.wind.rankraise.uitls.mViewHolder;

public class MainActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;
    List<String> listdata = new LinkedList<>();

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        listdata.add("基本工具");
        Mapapter mapapter = new Mapapter();
        listview.setAdapter(mapapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = listdata.get(i);
                if (str.equals("基本工具")) {
                    push(mContent, AllGuideActivity.class);
                }
            }
        });
    }

    class Mapapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public String getItem(int i) {
            return listdata.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (null == view) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_view, null);
            }
            TextView mTv_item_content = mViewHolder.get(view, R.id.mTv_item_content);
            mTv_item_content.setText(getItem(i));
            return view;
        }
    }
}
