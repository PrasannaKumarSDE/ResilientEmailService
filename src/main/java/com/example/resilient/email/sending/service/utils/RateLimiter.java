package com.example.resilient.email.sending.service.utils;

import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {
    private final int limit = 5;
    private final Queue<Long> timestamps = new LinkedList<>();

    public synchronized boolean allow() {
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() && now - timestamps.peek() > 60_000) {
            timestamps.poll();
        }
        if (timestamps.size() < limit) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
