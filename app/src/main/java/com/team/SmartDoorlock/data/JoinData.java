package com.team.SmartDoorlock.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("Did")
    private String Did;

    @SerializedName("Uid")
    private String Uid;

    @SerializedName("Pwd")
    private String Pwd;

    @SerializedName("Pnumber")
    private String Pnumber;

    @SerializedName("Relation")
    private String Relation;

    @SerializedName("Fimage")
    private String Fimage;

    public JoinData(String Did, String Uid, String Pwd, String Pnumber, String Relation, String Fimage) {
        this.Did = Did;
        this.Uid = Uid;
        this.Pwd = Pwd;
        this.Pnumber = Pnumber;
        this.Relation = Relation;
        this.Fimage = Fimage;
    }
}
