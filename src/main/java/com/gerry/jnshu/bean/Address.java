package com.gerry.jnshu.bean;

public class Address {
    public String id;
    public String province;
    private String city;
    private String distrinct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrinct() {
        return distrinct;
    }

    public void setDistrinct(String distrinct) {
        this.distrinct = distrinct;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", distrinct='" + distrinct + '\'' +
                '}';
    }
}
