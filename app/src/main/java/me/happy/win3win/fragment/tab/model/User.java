package me.happy.win3win.fragment.tab.model;

import lombok.Data;

/**
 * Created by JY on 2017-05-20.
 */

@Data
public class User {

    private String email;
    private String name;
    private String birth;
    private String wantJob;
    private String edu;
    private String address;
    private String phone;
    private String etccareer;
    private String certificate;
    private String specialNote;

    public User() {
    }

}
