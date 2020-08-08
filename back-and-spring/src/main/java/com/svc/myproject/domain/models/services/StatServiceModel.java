package com.svc.myproject.domain.models.services;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class StatServiceModel {
    private int requestCount;
    private Instant startedOn;

    public StatServiceModel(int requestCount, Instant startedOn) {
        this.requestCount = requestCount;
        this.startedOn = startedOn;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public Instant getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Instant startedOn) {
        this.startedOn = startedOn;
    }
}
