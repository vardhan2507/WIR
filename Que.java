package com.example.vardhan.wir;

import java.io.Serializable;

/**
 * Created by Vardhan on 9/27/2017.
 */

public class Que implements Serializable {
    String que;
    String o1,o2,o3,o4;
    String id;
    int ans;

   public Que(){

    }

    public Que(String que, String o1, String o2, String o3, String o4, String id, int ans) {
        this.que = que;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.id = id;
        this.ans = ans;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public void setO4(String o4) {
        this.o4 = o4;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public String getQue() {
        return que;
    }

    public String getO1() {
        return o1;
    }

    public String getO2() {
        return o2;
    }

    public String getO3() {
        return o3;
    }

    public String getO4() {
        return o4;
    }

    public String getId() {
        return id;
    }

    public int getAns() {
        return ans;
    }
}
