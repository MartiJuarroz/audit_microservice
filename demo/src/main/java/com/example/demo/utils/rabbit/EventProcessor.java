package com.example.demo.utils.rabbit;

@FunctionalInterface
public interface EventProcessor {
    void process(RabbitEvent event);
}
