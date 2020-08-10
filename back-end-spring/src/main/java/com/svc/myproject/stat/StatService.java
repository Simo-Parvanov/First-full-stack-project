package com.svc.myproject.stat;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatService {
    private AtomicInteger requestCount = new AtomicInteger(0);
    private Instant startedOn = Instant.now();

    public void incRequestCount(){
        requestCount.incrementAndGet();
    }
     public int getRequestCount(){
        return requestCount.get();
     }

     public Instant getStartedOn(){
        return startedOn;
     }
}
