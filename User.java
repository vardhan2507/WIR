package com.example.vardhan.wir;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vardhan on 9/27/2017.
 */

public class User implements Serializable{
    String uid;
    String Uusername;
    String fullname;
    int highscore;
    ArrayList<String> friends;
    ArrayList<Integer> requests;
    String fr;
    int ran;
    boolean keep;
    int curscore;
    User(){

    }

    public User(String uid, String uusername, String fullname, int highscore, ArrayList<String> friends, ArrayList<Integer> requests, String fr, int ran, boolean keep, int curscore) {
        this.uid = uid;
        Uusername = uusername;
        this.fullname = fullname;
        this.highscore = highscore;
        this.friends = friends;
        this.requests = requests;
        this.fr = fr;
        this.ran = ran;
        this.keep = keep;
        this.curscore = curscore;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUusername() {
        return Uusername;
    }

    public void setUusername(String uusername) {
        Uusername = uusername;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<Integer> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Integer> requests) {
        this.requests = requests;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public int getRan() {
        return ran;
    }

    public void setRan(int ran) {
        this.ran = ran;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public int getCurscore() {
        return curscore;
    }

    public void setCurscore(int curscore) {
        this.curscore = curscore;
    }
}
