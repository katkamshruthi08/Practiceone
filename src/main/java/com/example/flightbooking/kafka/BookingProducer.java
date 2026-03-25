package com.example.flightbooking.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service

public class BookingProducer {

    private final KafkaTemplate<Object, String> kafkaTemplate;

    public BookingProducer(KafkaTemplate<Object, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(String event) {

        System.out.println("Publishing booking event to Kafka: " + event);

        kafkaTemplate.send("booking-topic", event);
    }
}
