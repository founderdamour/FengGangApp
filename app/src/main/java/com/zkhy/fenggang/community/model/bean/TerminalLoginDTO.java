package com.zkhy.fenggang.community.model.bean;

/**
 * This is a Description
 *
 * @date 2018/12/21
 * 终端登录信息
 */
public class TerminalLoginDTO {

    // "用户信息")
    private UserDTO user;

    // "用户token")
    private Token userToken;

    //  "应用级token")
    private Token token;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Token getUserToken() {
        return userToken;
    }

    public void setUserToken(Token userToken) {
        this.userToken = userToken;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
