package com.example.vardhan.wir;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Vardhan on 10/8/2017.
 */

public class mque {
    String s;
    ArrayList<Que> ques;
    ArrayList<String> name;
    public mque()
    {

    }

    public mque(String s, ArrayList<Que> ques, ArrayList<String> name) {
        this.s = s;
        this.ques = ques;
        this.name = name;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public ArrayList<Que> getQues() {
        return ques;
    }

    public void setQues(ArrayList<Que> ques) {
        this.ques = ques;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }
}
