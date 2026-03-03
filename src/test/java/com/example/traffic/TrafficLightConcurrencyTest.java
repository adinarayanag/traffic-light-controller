package com.example.traffic;

import com.example.traffic.service.TrafficLightService;
import com.example.traffic.model.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightConcurrencyTest {

    @Test
    void concurrentGreenRequestsShouldAllowOnlyOne() throws Exception {

        TrafficLightService service = new TrafficLightService();
        service.createIntersection("c1");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        Runnable task1 = () -> {
            try {
                service.changeLight("c1", Direction.NORTH_SOUTH, LightColor.GREEN);
                successCount.incrementAndGet();
            } catch (Exception e) {
                failureCount.incrementAndGet();
            }
        };

        Runnable task2 = () -> {
            try {
                service.changeLight("c1", Direction.EAST_WEST, LightColor.GREEN);
                successCount.incrementAndGet();
            } catch (Exception e) {
                failureCount.incrementAndGet();
            }
        };

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1, successCount.get(), "Only one request should succeed");
        assertEquals(1, failureCount.get(), "One request should fail due to conflict");
    }
}