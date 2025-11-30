package com.sender.Sender.controller;

import com.sender.Sender.model.Msg;
import com.sender.Sender.service.SendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sender")
public class SenderController {

    @Autowired
    SendMsgService sendMsgService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody String msg,@RequestParam String topic){
        return  ResponseEntity.ok(sendMsgService.sendMsg(topic,msg));
    }

}
