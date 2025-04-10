package com.uolhost.challenge.model;

public class Codename {

    private String name;
    private boolean used;

    public Codename() {

    }

    public Codename(String name, boolean used) {
        this.name = name;
        this.used = used;
    }

    public String getName() {
        return name;
    }

    public boolean isUsed() {
        return used;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
