package com.trebuchet.controller;

import com.trebuchet.database.DataBaseController;

import com.trebuchet.database.FrontendSettings;
import com.trebuchet.database.MyStromTable;
import com.trebuchet.restclient.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Configuration
@CrossOrigin
@Component
public class HttpController {

    private final DataBaseController dataBaseController;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpController.class);
    static RequestService clientServerRoom = new RequestService("http://192.168.1.90:80/report","Server Raum");
    static RequestService clientOffice = new RequestService("http://192.168.1.97:80/report","Büro");
    static RequestService kraken = new RequestService("http://192.168.1.220:5000","Kraken");

    @Autowired
    public HttpController(DataBaseController dataBaseController){
        this.dataBaseController = dataBaseController;
        startTrending();

    }

    @GetMapping("/")
    public String displayMessageFor8080(){
        return "The Application is running, see /Logs/Log.log for more information";
    }

    //TODO more db queries have to be
    @GetMapping("/list/{deviceName}")
    public List<MyStromTable> indexList(@PathVariable String deviceName) {
        LOGGER.info("Get List of:" + deviceName);
        return dataBaseController.getList(deviceName);
    }

    @GetMapping("/settings")
    public FrontendSettings getSettings(){
        return dataBaseController.getFrontendSettings();
    }

    @PostMapping("/settings")
    public ResponseEntity<FrontendSettings> setSettings(@RequestBody FrontendSettings frontendSettings){
        dataBaseController.getFrontendSettings().setReportHours(frontendSettings.getReportHours());
        return new ResponseEntity<FrontendSettings>(frontendSettings, HttpStatus.OK);
    }

    public void startTrending(){
        dataBaseController.startDatabaseEntry(clientServerRoom);
        dataBaseController.startDatabaseEntry(clientOffice);
    }

    @GetMapping("/kraken")
    public String loginAndGetDataKraken()  {
        /*String accessToken = kraken.requestKrakenToken("/webapi/auth.cgi?api=SYNO.API.Auth&version=3&method=login&account=Andreas&passwd=4556@A89xy$$&session=FileStation&format=cookie");
        try {
            return kraken.GET_KrakenInfo("/webman/modules/SystemInfoApp/StorageUsageWidget.js",accessToken).toString();
        } catch (Exception e){
            e.printStackTrace();
        }*/
        return null;
    }

}
