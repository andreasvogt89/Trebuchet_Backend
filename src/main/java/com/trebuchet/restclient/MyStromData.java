package com.trebuchet.restclient;
import com.fasterxml.jackson.annotation.JsonProperty;


public class MyStromData {
    //JOSN Example:
    // {"power":48.364818572998047,"Ws":48.167659759521484,"relay":true,"temperature":24.618339538574219}

    @JsonProperty("power")
    private Double power;
    @JsonProperty("Ws")
    private Double energy;
    @JsonProperty("relay")
    private Boolean relay;
    @JsonProperty("temperature")
    private Double temperature;

    public MyStromData() {
    }

    public Double getPower() {
        long factor = (long) Math.pow(10, 2);
        power = power * factor;
        long tmp = Math.round(power);
        return (double) tmp / factor;
    }

    public Double getEnergy() {
        long factor = (long) Math.pow(10, 2);
        energy = energy * factor;
        long tmp = Math.round(energy);
        return (double) tmp / factor;

    }

    public Boolean getRelay() {
        return relay;
    }

    public Double getTemperature() {
        long factor = (long) Math.pow(10, 2);
        temperature = temperature * factor;
        long tmp = Math.round(temperature);
        return (double) tmp / factor;
    }

}
