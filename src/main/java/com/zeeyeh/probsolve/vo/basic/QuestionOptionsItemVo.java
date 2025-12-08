package com.zeeyeh.probsolve.vo.basic;

public class QuestionOptionsItemVo {
    private int index;
    private String value;

    public QuestionOptionsItemVo() {
    }

    public QuestionOptionsItemVo(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
