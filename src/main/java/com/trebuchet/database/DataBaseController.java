package com.trebuchet.database;

import com.trebuchet.restclient.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DataBaseController {

    @Autowired
    DataRepository dataRepository;

    private HashMap<String,MyStromTable> myStromTableList = new HashMap<>();

    public void startDatabaseEntry(Client client) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                MyStromTable myStromTable = new MyStromTable(client.getMyStromData(),client.getName());
                dataRepository.save(myStromTable);
                myStromTableList.put(myStromTable.getDeviceName(),myStromTable);
            }
        }, 0, 1000);

    }

    public HashMap<String, MyStromTable> getMyStromTableList() {
        return myStromTableList;
    }

    public List<MyStromTable> getList(String deviceName){
        return dataRepository.findAllByDeviceName(deviceName);
    }
}
