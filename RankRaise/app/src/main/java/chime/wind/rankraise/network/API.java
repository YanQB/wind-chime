package chime.wind.rankraise.network;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by truekey on 17/9/18.
 */

public interface API {

    /**
     * 发送验证码
     *
     * @param cPhone
     * @return
     */
    @POST("user/getVerifyCode")
    Call<ResponseBody> getVerifyCode(@Query("cPhone") String cPhone);

    /**
     * 上传用户头像
     *
     * @param params
     * @return
     */
    @Multipart
    @POST("user/uploadHeadImg")
    Call<ResponseBody> uploadHeadImg(@Part List<MultipartBody.Part> params);
}
