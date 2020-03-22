package com.trebuchet.restclient;



import org.springframework.web.client.RestTemplate;


public class Client {
    private String uri;
    private RestTemplate restTemplate;
    private String name;

    public Client(String uri, String name){
        this.uri = uri;
        this.restTemplate = new RestTemplate();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MyStromData getMyStromData(){
        return restTemplate.getForObject(uri, MyStromData.class);
    }

}
