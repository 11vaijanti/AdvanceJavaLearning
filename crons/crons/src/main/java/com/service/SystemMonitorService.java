package com.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemMonitorService {
    private int totalChecks = 0;

    public void checkSystemStatus() {

        totalChecks++;

        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                ));

        // Simulated status for learning purposes
        String status = "RUNNING";

        System.out.println("----- SYSTEM STATUS REPORT -----");
        System.out.println("Time: " + currentTime);
        System.out.println("Status: " + status);
        System.out.println("Total checks: " + totalChecks);
        System.out.println("--------------------------------");
    }
}
