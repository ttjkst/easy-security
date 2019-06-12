package org.easySecurity.core.user;

import org.springframework.beans.BeanUtils;

public class ClientUserInfoEntity extends UserInfoEntity {

    private String tokenValue;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public ClientUserInfoEntity(){

    }
    public ClientUserInfoEntity(UserInfoEntity entity, String tokenValue){
        BeanUtils.copyProperties(entity,this);
        this.tokenValue = tokenValue;
    }
}
