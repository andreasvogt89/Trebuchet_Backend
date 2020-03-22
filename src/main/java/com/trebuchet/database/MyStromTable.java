package com.trebuchet.database;



import com.trebuchet.restclient.MyStromData;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
public class MyStromTable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private java.sql.Timestamp timestamp;
    private String deviceName;
    private Double power;
    private Double energy;
    private Boolean relay;
    private Double temperature;

    public MyStromTable() {
    }

    public MyStromTable(MyStromData data, String deviceName) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.power = data.getPower();
        this.energy = data.getEnergy();
        this.relay = data.getRelay();
        this.temperature = data.getTemperature();
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        this.deviceName = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Boolean getRelay() {
        return relay;
    }

    public void setRelay(Boolean relay) {
        this.relay = relay;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
