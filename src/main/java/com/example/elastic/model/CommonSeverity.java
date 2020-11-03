package com.example.elastic.model;

public class CommonSeverity {

    private String id;

    private Integer cont;

    public CommonSeverity(String id, Integer cont) {
        this.id = id;
        this.cont = cont;
    }

    public CommonSeverity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String anId) {
        this.id = anId;
    }

    public Integer getCont() {
        return this.cont;
    }

    public void setCont(Integer aCont) {
        this.cont = aCont;
    }

}
