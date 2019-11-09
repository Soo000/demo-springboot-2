package com.alisls.demo.springboot.jpa.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -5171786490573585406L;
	
	private Integer userId;
    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
