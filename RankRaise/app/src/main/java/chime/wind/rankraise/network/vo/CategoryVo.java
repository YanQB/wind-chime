package chime.wind.rankraise.network.vo;

import android.support.annotation.NonNull;

/**
 * 用于multitype 提供数据类型
 * Created by truekey on 18/2/28.
 */

public class CategoryVo extends BaseVo {
    @NonNull
    private String text;

    public CategoryVo(@NonNull String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
