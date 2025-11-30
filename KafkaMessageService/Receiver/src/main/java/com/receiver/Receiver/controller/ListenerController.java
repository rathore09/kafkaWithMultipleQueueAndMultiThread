package com.receiver.Receiver.controller;


import com.receiver.Receiver.appConfig.DynamicKafkaListenerManager;
import com.receiver.Receiver.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    @Autowired
    DynamicKafkaListenerManager manager;

    @GetMapping("/start")
    public ResponseEntity<Queue> startController(@RequestParam String name){
        return ResponseEntity.ok(manager.startListener(name));
    }

    @GetMapping("/stop")
    public ResponseEntity<Queue> stopController(@RequestParam String name){
        return ResponseEntity.ok(manager.stopListener(name));
    }
}
