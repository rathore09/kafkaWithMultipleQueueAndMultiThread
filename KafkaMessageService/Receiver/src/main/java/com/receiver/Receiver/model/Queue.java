package com.receiver.Receiver.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "queue")
public class Queue {
    @Id
    private  Integer id;
    @Column(name = "queue_name")
    private  String name;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "consumer")
    private Integer consumer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getConsumer() {
        return consumer;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", consumer=" + consumer +
                '}';
    }

    public void setConsumer(Integer consumer) {
        this.consumer = consumer;
    }
}
