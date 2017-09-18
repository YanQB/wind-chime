package chime.wind.rankraise.network;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by truekey on 1
 * Data: 2016/10/11.
 */
public class NetWorkClient {

    private static final String URL = "";//API服务器地址
    private static API api;


    public static API getAPI() {
        if (api == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            //添加header
                            String iUserID = null;
                            String token = null;
                            if (TextUtils.isEmpty(iUserID) || TextUtils.isEmpty(token)) {
                                return chain.proceed(original.newBuilder().build());
                            }

                            Log.e("Header", "iCustomerID: " + iUserID + "   token:" + token);
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("iUserID", iUserID)
                                    .header("token", token);

                            return chain.proceed(requestBuilder.build());
                        }
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(API.class);
        }
        return api;
    }

}
