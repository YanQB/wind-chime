package chime.wind.rankraise.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import chime.wind.rankraise.network.vo.BaseVo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * User: Created by MoMo - Neng. on
 * Data: 2016/10/11.
 */
public class BaseCallCallback {

    InterfaceCall interCallback;
    private Context mContent;
    private Class classOfT;


    public BaseCallCallback(Context content, Class classOfT, InterfaceCall interCallback) {
        this.mContent = content;
        this.classOfT = classOfT;
        this.interCallback = interCallback;
    }

    public Callback<ResponseBody> callCallback = new Callback<ResponseBody>() {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Log.e("HttpUrl： ", response.raw().request().url().url().getFile());
            try {
                int code = response.code();
                BaseVo baseVo = null;
                if (code == 200) {
                    String json = getResponseJSON(response);
                    if (!TextUtils.isEmpty(json)) {
                        baseVo = (BaseVo) new Gson().fromJson(json, classOfT);
                        String message = baseVo.getMessage();
                        int codee = baseVo.getCode();
                        switch (codee) {
                            case 200:
                                interCallback.success(baseVo);
                                break;
                            case 500:
                                //服务器错误
                                interCallback.failure(message);
                                break;
                            default:
                                //解析错误
                                interCallback.failure(message);
                                break;
                        }
                        return;
                    }
                    interCallback.success(baseVo);
                } else {
                    interCallback.failure("网络请求异常");
                }
            } catch (Exception e) {
                e.printStackTrace();
                interCallback.failure("Exception error");
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            interCallback.failure("网络错误异常");
        }
    };


    public interface InterfaceCall {
        void success(BaseVo baseVo);

        void failure(String message);
    }


    /**
     * @param response
     * @return
     */
    private String getResponseJSON(Response<ResponseBody> response) {
        String s = "";
        try {
            s = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
