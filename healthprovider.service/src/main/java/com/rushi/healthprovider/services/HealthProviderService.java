package com.rushi.healthprovider.services;

import com.rushi.healthprovider.models.HealthProvider;

import java.time.LocalDate;
import java.util.List;

public interface HealthProviderService {

    String createHealthProvider(HealthProvider healthProvider);

    List<HealthProvider> getAllHealthProvidersAvailableByDateAndDepartment(LocalDate selectedDate, String department);

}
