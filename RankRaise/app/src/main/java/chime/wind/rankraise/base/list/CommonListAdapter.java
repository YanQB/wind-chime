package chime.wind.rankraise.base.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class CommonListAdapter<T> extends BaseAdapter {

    /**
     * Context
     */
    Context mContext;
    /**
     * 要展示的数据列表
     */
    List<T> mData;
    /**
     * 每一项的布局id,例如R.layout.my_listview_item.
     */
    private int mItemLayoutId = -1;

    /**
     * @param context         Context
     * @param itemLayoutResId 每一项(适用于listview、gridview等AbsListView子类)的布局资源id,例如R.layout.
     *                        my_listview_item.
     * @param dataSource      数据源
     */
    public CommonListAdapter(Context context, int itemLayoutResId, List<T> dataSource) {
        checkParams(context, itemLayoutResId, dataSource);
        mContext = context;
        mItemLayoutId = itemLayoutResId;
        this.mData = dataSource;
    }

    /**
     * 检查参数的有效性
     *
     * @param context
     * @param itemLayoutResId
     * @param dataSource
     */
    private void checkParams(Context context, int itemLayoutResId, List<T> dataSource) {
        if (context == null || itemLayoutResId < 0 || dataSource == null) {
            throw new RuntimeException(
                    "context == null || itemLayoutResId < 0 || dataSource == null, please check your params");
        }
    }

    /**
     * 返回数据的总数
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * 返回position位置的数据
     */
    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * item id, 返回position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回position位置的view, 即listview、gridview的第postion个view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取ViewHolder
        CommonListViewHolder viewHolder = CommonListViewHolder.getViewHolder(mContext, convertView, parent,
                mItemLayoutId);
        // 填充数据
        fillItemData(viewHolder, position, getItem(position));
        // 返回convertview
        return viewHolder.getContentView();
    }

    /**
     * 用户必须覆写该方法来讲数据填充到视图中
     *
     * @param viewHolder 通用的ViewHolder, 里面会装载listview,
     *                   gridview等组件的每一项的视图，并且缓存其子view
     * @param item       数据源的第position项数据
     */
    protected abstract void fillItemData(CommonListViewHolder viewHolder, int position, T item);


    public List<T> getmDatas() {
        return mData;
    }

    public void setmData(List<T> mData) {
        this.mData = mData;
    }

    public void addDatasTop(final List<T> list) {
        if (list != null && list.size() >= 0) {
            getmDatas().clear();
            addDatas(list);
        }
    }

    public void addDatas(List<T> list) {
        if (list != null && list.size() >= 0) {
            getmDatas().addAll(list);
            notifyDataSetChanged();
        }

    }

    public void addDatasReverse(List<T> list) {
        if (list != null && list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                getmDatas().add(i, list.get(i));
            }
            notifyDataSetChanged();
        }

    }

}