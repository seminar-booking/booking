package com.example.member.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledJobExecutor {

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);

    public static void scheduleJob(Runnable job, int delay, TimeUnit timeUnit) {

        CompletableFuture
                .runAsync(() -> executor.schedule(job, delay, timeUnit));
    }
}
