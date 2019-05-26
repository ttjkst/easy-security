package org.easySecurity.core.user;

import org.springframework.beans.BeanUtils;

public class ClientUserInfoEnity extends UserInfoEnity {
        private String tokenValue;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public ClientUserInfoEnity(){

    }
    public ClientUserInfoEnity(UserInfoEnity enity,String tokenValue){
        BeanUtils.copyProperties(enity,this);
        this.tokenValue = tokenValue;
    }
}
