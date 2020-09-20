package com.team.SmartDoorlock.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("Uid")
    String Uid;

    @SerializedName("Password")
    String Password;

    public LoginData(String Uid, String Password) {
        this.Uid = Uid;
        this.Password = Password;
    }
}
