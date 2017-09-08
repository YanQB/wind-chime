package chime.wind.rankraise.uitls;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by truekey on 16/3/29.
 */
public class mViewHolder {
    //当有很多很多的ViewAdapter要写ViewHolder 的时候
    // I added a generic return type to reduce the casting noise in client code
    private mViewHolder(){
        //防止mViewHolder外部被实例化
    }
    @SuppressWarnings("unchecked")
    public static <T> T get(View view, int id) {

        SparseArray viewHolder = (SparseArray) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray();
            view.setTag(viewHolder);
        }
        View childView = (View) viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id,childView);
        }
        return (T) childView;

    }
}
