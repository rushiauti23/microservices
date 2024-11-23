package com.rushi.healthprovider.controllers;

import com.rushi.healthprovider.models.HealthProvider;
import com.rushi.healthprovider.services.HealthProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class HealthProviderController {

    private HealthProviderService healthProviderService;

    @PostMapping("/api/v1/healthproviders/create")
    public ResponseEntity<String> createHealthProvider(@Valid @RequestBody HealthProvider healthProvider){
        try{
                return ResponseEntity.ok(healthProviderService.createHealthProvider(healthProvider));
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/api/v1/healthproviders/available")
    public ResponseEntity<List<HealthProvider>> getAllHealthProvidersAvailableByDateAndDepartment(@RequestParam LocalDate selectedDate, @RequestParam String department){
        try {
            return ResponseEntity.ok(healthProviderService.getAllHealthProvidersAvailableByDateAndDepartment(selectedDate, department));
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
