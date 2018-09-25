package com.devopsbuddy.enums;

public enum PlansEnum {
    BASIC(1,"Basic"), PRO(2, "Pro");

    private final int id;
    private final String planName;


    private PlansEnum(int id, String name){
        this.id=id;
        this.planName=name;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }

}
