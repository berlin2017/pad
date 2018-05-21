package com.berlin.testpad.socre.model;

import org.litepal.crud.DataSupport;

/**
 * Created by ahxmt on 2018/5/21.
 */

public class ScoreModel extends DataSupport{
    private int id;
    private boolean isAllDone;
    private String fragment1;
    private String fragment2;
    private String fragment3;
    private String fragment4;
    private String fragment5;

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
