package com.lizhi.demo.filter;

/**
 * @author: lizhi
 * @Date: 2019/11/18 11:25
 * @Description:
 */
public class UserContext {


    public static final String USER_ID = "user_id";
    public static final String USER_TOKEN = "user_token";
    public static final String CORRELATION_ID = "correlation_id";

    private String userId;
    private String userToken;
    private String correlationId;

    public String getUserId() {
        return userId;
    }

    public UserContext setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserToken() {
        return userToken;
    }

    public UserContext setUserToken(String userToken) {
        this.userToken = userToken;
        return this;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public UserContext setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "userId='" + userId + '\'' +
                ", userToken='" + userToken + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
