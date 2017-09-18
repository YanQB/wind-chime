package chime.wind.rankraise.ui;

import android.os.Bundle;

import java.io.File;

import chime.wind.rankraise.base.BaseActivity;
import chime.wind.rankraise.network.BaseCallCallback;
import chime.wind.rankraise.network.NetWorkClient;
import chime.wind.rankraise.network.vo.BaseVo;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by truekey on 17/9/18.
 */

public class APIExample extends BaseActivity {
    private void example() {
        File file = new File("filepath");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("file", "filename.png", MultipartBody.create(MultipartBody.FORM, file));
        Call<ResponseBody> call = NetWorkClient.getAPI().uploadHeadImg(builder.build().parts());
        call.enqueue(new BaseCallCallback(mContent, BaseVo.class, new BaseCallCallback.InterfaceCall() {
            @Override
            public void success(BaseVo baseVo) {

            }

            @Override
            public void failure(String message) {

            }
        }).callCallback);
    }

    @Override
    protected int setContentView() {
        return 0;
    }

    @Override
    protected void onAfterCreate(Bundle savedInstanceState) {

    }
}
