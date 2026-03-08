package com.example.flightbooking.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class  BookingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BookingProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(String message) {
        kafkaTemplate.send("booking-topic", message);
    }
}
