package com.sender.Sender.service;

import com.sender.Sender.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMsgServiceImpl implements SendMsgService{

    @Value("${app.kafka.topic}")
    private String topic;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SendMsgServiceImpl.class);
    @Override
    public String sendMsg(String topic,String msg) {
      kafkaTemplate.send(topic,msg);
        return "msg send succefully ";
    }
    @Override
    public String receiveMsg(String msg) {
        return "this is msg "+msg;
    }


}
