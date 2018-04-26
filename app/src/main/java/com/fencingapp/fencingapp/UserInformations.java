package com.fencingapp.fencingapp;

import android.net.Uri;

import java.net.URI;

/**
 * Created by geoff on 29-03-17.
 */

public class UserInformations {
    public String all_name,email,avatar_url;
    public Integer age;

    public UserInformations(){

    }

    public UserInformations(String all_name,String email,Integer age, Uri avatar_url){
        this.all_name = all_name;
        this.email = email;
        this.age = age;
        this.avatar_url = avatar_url.toString();
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }




}
