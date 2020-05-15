package com.trebuchet.restclient;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class RequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestService.class);
    private final String uri;
    private final RestTemplate restTemplate;
    private final String name;

    public RequestService(String uri, String name){
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

    public String requestKrakenToken (String uri){
        return restTemplate.getForObject(this.uri + uri, Kraken.KrakenLogin.class).getSid();
    }

    public Kraken GET_KrakenInfo(String uri, String accessToken){
        //?_dc=1588172837464&SynoToken=" + token + "&query=systemHealth"
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("v", "24922-s4");
        map.add("SynoToken", accessToken);
        return restTemplate.getForObject(uri,Kraken.class,new HttpEntity<>(map,httpHeaders));
    }

}
