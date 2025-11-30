package com.receiver.Receiver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceiverServiceImpl implements ReceiverService{
    private static final Logger logger= LoggerFactory.getLogger(ReceiverServiceImpl.class);

    @Override
    public String receive(String msg) {
     logger.info("{} ,This is msg {}",Thread.currentThread().getName(),msg);
         try {
             Thread.sleep(10000*6); //checking working of  thread with kafka
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     return msg;
    }

}
