package com.example.week4daily3homework.model.user_info;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
    String email;
    String password;
    String name;
    String dob;

    public UserInfo() {
    }

    public UserInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected UserInfo(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }
}
