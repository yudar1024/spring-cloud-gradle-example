package com.roger.springcloud.services;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenluo on 9/22/2016.
 */
public class Vo1 implements Serializable {



    private String userName;
    private int age;

    private Vo2 vo2;

    private List<Vo2> volist;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Vo2 getVo2() {
        return vo2;
    }

    public void setVo2(Vo2 vo2) {
        this.vo2 = vo2;
    }

    public List<Vo2> getVolist() {
        return volist;
    }

    public void setVolist(List<Vo2> volist) {
        this.volist = volist;
    }
}

