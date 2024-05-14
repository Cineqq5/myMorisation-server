package pl.com.morten.MyMorisation.dto;

import pl.com.morten.MyMorisation.configuration.UserState;

public class UserInfoDto {
    UserState userState;
    long id;
    String role;

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
