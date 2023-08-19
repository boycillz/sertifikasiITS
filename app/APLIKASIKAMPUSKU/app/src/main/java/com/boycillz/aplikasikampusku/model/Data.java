package com.boycillz.aplikasikampusku.model;


public class Data {
    private String id;
    private String nomor;
    private String name;
    private String tl;
    private String jk;
    private String address;

    public Data() {
        this.id = "";
        this.nomor = "";
        this.name = "";
        this.tl = "";
        this.jk = "";
        this.address = "";
    }

    public Data(String id, String nomor, String name, String tl, String jk, String address) {
        this.id = id;
        this.nomor = nomor;
        this.name = name;
        this.tl = tl;
        this.jk = jk;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
