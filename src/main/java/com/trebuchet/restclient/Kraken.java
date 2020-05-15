package com.trebuchet.restclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


public class Kraken {

    public static class KrakenLogin{
        //{"data":{"sid":"oSIFFIwhehtFM1860PDN510612"},"success":true}
        @JsonPropertyDescription("data")
        @JsonProperty("sid")
        private String sid;

        @JsonProperty("success")
        private boolean connectable;

        public String getSid() {
            return sid;
        }

        public boolean isConnectable() {
            return connectable;
        }
    }
}
