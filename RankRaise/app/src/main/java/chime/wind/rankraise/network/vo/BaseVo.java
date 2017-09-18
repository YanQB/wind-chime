package chime.wind.rankraise.network.vo;

/**
 * Created by truekey on 17/9/18.
 */

public class BaseVo {
    /**
     * code : 200
     * message : ok
     */

    private int code;
    private String message;
    private String token;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
