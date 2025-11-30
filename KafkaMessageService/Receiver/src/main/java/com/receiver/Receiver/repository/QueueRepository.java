package com.receiver.Receiver.repository;

import com.receiver.Receiver.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue,Integer> {
    Queue findByName(String name);
}
