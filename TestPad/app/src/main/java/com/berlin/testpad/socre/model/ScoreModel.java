package com.berlin.testpad.socre.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class ScoreModel extends DataSupport implements Serializable {
    private int id;
    private boolean isAllDone;
    private String fragment1;
    private String fragment2;
    private String fragment3;
    private String fragment4;
    private String fragment5;
    private int user_id;
    private String file_path;
    private String file_name;
    private boolean save_success;

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public boolean isSave_success() {
        return save_success;
    }

    public void setSave_success(boolean save_success) {
        this.save_success = save_success;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAllDone() {
        return isAllDone;
    }

    public void setAllDone(boolean allDone) {
        isAllDone = allDone;
    }

    public String getFragment1() {
        return fragment1;
    }

    public void setFragment1(String fragment1) {
        this.fragment1 = fragment1;
    }

    public String getFragment2() {
        return fragment2;
    }

    public void setFragment2(String fragment2) {
        this.fragment2 = fragment2;
    }

    public String getFragment3() {
        return fragment3;
    }

    public void setFragment3(String fragment3) {
        this.fragment3 = fragment3;
    }

    public String getFragment4() {
        return fragment4;
    }

    public void setFragment4(String fragment4) {
        this.fragment4 = fragment4;
    }

    public String getFragment5() {
        return fragment5;
    }

    public void setFragment5(String fragment5) {
        this.fragment5 = fragment5;
    }
}
