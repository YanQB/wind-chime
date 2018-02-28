package chime.wind.rankraise.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chime.wind.rankraise.R;
import chime.wind.rankraise.network.vo.CategoryVo;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by truekey on 18/2/28.
 */

public class CategoryViewBinder extends ItemViewBinder<CategoryVo,CategoryViewBinder.ViewHolder>{

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CategoryVo item) {
        holder.category.setText(item.getText());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final TextView category;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}
