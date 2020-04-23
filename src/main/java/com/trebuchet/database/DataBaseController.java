package com.trebuchet.database;

import com.trebuchet.restclient.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;


@Component
public class DataBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseController.class);
    private FrontendSettings frontendSettings;
    private DataRepository dataRepository;

    @Autowired
    public DataBaseController(FrontendSettings frontendSettings, DataRepository dataRepository) {
        this.frontendSettings = frontendSettings;
        this.dataRepository = dataRepository;

    }

    public void startDatabaseEntry(Client client) {
        Timer timer = new Timer();
        LOGGER.info("Start Trending for: " + client.getName());
        timer.schedule(new TimerTask() {
            public void run() {
                MyStromTable myStromTable = new MyStromTable(client.getMyStromData(),client.getName());
                dataRepository.save(myStromTable);
            }
        }, 0, 15 * 60000);// 15min
    }

    public List<MyStromTable> getList(String deviceName){
        return dataRepository.findByDeviceNameAndTimestamp(deviceName, getAnalyticDate());
    }

    public Timestamp getAnalyticDate(){
        return new Timestamp(System.currentTimeMillis() - frontendSettings.getReportHours() * 3600 * 1000);
    }

    public FrontendSettings getFrontendSettings() {
        return frontendSettings;
    }

}
