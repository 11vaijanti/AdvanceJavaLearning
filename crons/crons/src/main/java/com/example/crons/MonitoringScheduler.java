package com.example.crons;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitoringScheduler {

    @Scheduled(fixedRate = 10000)
    public void runMonitoringTask() {
        System.out.println("Scheduler is running!");
    }
}