package com.example.flightbooking.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumer {

    @KafkaListener(topics = "booking-topic", groupId = "booking-group")
    public void consume(String message) {

        System.out.println("********************Received message from Kafka: **********************" + message);

    }
}