package com.receiver.Receiver.appConfig;

import com.receiver.Receiver.listener.DynamicKafkaListener;
import com.receiver.Receiver.model.Queue;
import com.receiver.Receiver.repository.QueueRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DynamicKafkaListenerManager {

    private final static Logger logger = LoggerFactory.getLogger(DynamicKafkaListenerManager.class);
    private final Map<String, ConcurrentMessageListenerContainer<String,String>> containerMap =new HashMap<>();

    @Autowired
    ConcurrentKafkaListenerContainerFactory<String,String> factory;

    @Autowired
    private DynamicKafkaListener messageHandler;

    @Autowired
    QueueRepository queueRepository;

    @PostConstruct
    public void startListener() {
        List<Queue> queues =  queueRepository.findAll();
        logger.info("this is value of all {}",queues);

       List<Queue> actives = queues.stream().filter(x->{
           return x.getActive();
       }).toList();
       logger.info("this is value of all activve {}",actives);

       for(int i =0;  i<actives.size(); i++){
           ConcurrentMessageListenerContainer<String, String> container =
                   factory.createContainer(actives.get(i).getName());
           container.setConcurrency(actives.get(i).getConsumer());
           container.getContainerProperties().setMessageListener(messageHandler);
           container.start();
           containerMap.put(actives.get(i).getName(),container);
          logger.info("message listener add for queue {}",actives.get(i).getName());
       }
    }

    public Queue startListener(String name){
        Queue queue =queueRepository.findByName(name);
        ConcurrentMessageListenerContainer<String,String> container =factory.createContainer(name);
        container.setConcurrency(queue.getConsumer());
        container.getContainerProperties().setMessageListener(messageHandler);
        container.start();
        queue.setActive(true);

        containerMap.put(name,container);
        return queueRepository.save(queue);
    }

    public Queue stopListener(String name){
        Queue queue =queueRepository.findByName(name);
        ConcurrentMessageListenerContainer<String ,String>  container = containerMap.get(name);
        container.stop();
        containerMap.remove(name);
        queue.setActive(false);
        return queueRepository.save(queue);
    }
}
