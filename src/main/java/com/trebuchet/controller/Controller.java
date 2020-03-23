package com.trebuchet.controller;

import com.trebuchet.database.DataBaseController;

import com.trebuchet.database.MyStromTable;
import com.trebuchet.restclient.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Configuration
@CrossOrigin
public class Controller {

    private DataBaseController dataBaseController;

    @Autowired
    public Controller(DataBaseController dataBaseController){
        this.dataBaseController = dataBaseController;
        startTrending();
    }

    @GetMapping("/ms")
    public List<MyStromTable> getDevices() {
        System.out.println("GET devices");
        return new ArrayList<>(dataBaseController.getMyStromTableList().values());
    }

    //TODO
    @GetMapping("/list/{deviceName}")
    public List<MyStromTable> indexList(@PathVariable String deviceName) {
        System.out.println("GET list from" + deviceName);
        return dataBaseController.getList(deviceName);
    }

    public void startTrending(){
        Client clientServerRoom = new Client("http://192.168.1.90:80/report","Server Raum");
        Client clientOffice = new Client("http://192.168.1.97:80/report","Büro");
        dataBaseController.startDatabaseEntry(clientServerRoom);
        dataBaseController.startDatabaseEntry(clientOffice);
    }
}
