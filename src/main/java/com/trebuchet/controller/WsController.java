package com.trebuchet.controller;

import com.trebuchet.database.MyStromTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@Configuration
@CrossOrigin
@Component
public class WsController {

    private final SimpMessagingTemplate template;
    private static final Logger LOGGER = LoggerFactory.getLogger(WsController.class);

    private final ArrayList<MyStromTable> currentMyStromData = new ArrayList<>();

    public WsController(SimpMessagingTemplate template){
        this.template = template;
        startStream();
    }


    @MessageMapping("/submystrom")
    @SendTo("/broker/submystrom")
    public void getDevices() {

    }

    @MessageMapping("/send")
    public void getMessage(String message){
        System.out.println("Message from socket" + message);
    }

    public void startStream(){
        Timer timer = new Timer();
        LOGGER.info("Start Streaming devices");
        timer.schedule(new TimerTask() {
            public void run() {
                template.convertAndSend("/broker/submystrom", getCurrentMyStromData());
            }
        }, 0, 500);
    }

    public ArrayList<MyStromTable> getCurrentMyStromData() {
        currentMyStromData.clear();
        MyStromTable tempOffice = new MyStromTable(HttpController.clientOffice.getMyStromData()
                ,HttpController.clientOffice.getName());
        MyStromTable tempServer = new MyStromTable(HttpController.clientServerRoom.getMyStromData()
                ,HttpController.clientServerRoom.getName());
        currentMyStromData.add(tempOffice);
        currentMyStromData.add(tempServer);
        return currentMyStromData;
    }
}
