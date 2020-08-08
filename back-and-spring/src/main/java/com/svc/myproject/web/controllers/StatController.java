package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.StatServiceModel;
import com.svc.myproject.stat.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/statistic")
public class StatController {
    private final StatService statService;
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping()
    public ResponseEntity<?> getStatistic(){
    StatServiceModel statServiceModel = new StatServiceModel(statService.getRequestCount(), statService.getStartedOn());
        return ResponseEntity.ok(statServiceModel);
    }
}
