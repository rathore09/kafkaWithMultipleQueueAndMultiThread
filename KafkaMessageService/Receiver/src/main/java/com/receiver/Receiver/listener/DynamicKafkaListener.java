package com.receiver.Receiver.listener;

import com.receiver.Receiver.service.ReceiverService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class DynamicKafkaListener implements MessageListener<String,String> {

    private static final Logger logger = LoggerFactory.getLogger(DynamicKafkaListener.class);

    @Autowired
    ReceiverService receiverService;

    @Autowired
    Executor executor;

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        logger.info("This is msg  queue from where i am listening msg's {}",data);

        executor.execute(()->{
            String value = data.value();
            receiverService.receive(value);
        });


    }
}
