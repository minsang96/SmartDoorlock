package com.team.SmartDoorlock.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("Uid")
    String Uid;

    @SerializedName("Pwd")
    String Pwd;

    public LoginData(String Uid, String Pwd) {
        this.Uid = Uid;
        this.Pwd = Pwd;
    }
}
