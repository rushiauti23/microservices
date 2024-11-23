package com.rushi.healthprovider.services;

import com.rushi.healthprovider.models.HealthProvider;
import com.rushi.healthprovider.repositories.HealthProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class HealthProviderServiceImpl implements HealthProviderService{


    private HealthProviderRepository healthProviderRepository;


    @Override
    public String createHealthProvider(HealthProvider healthProvider) {
        return healthProviderRepository.save(healthProvider).getId();
    }

    @Override
    public List<HealthProvider> getAllHealthProvidersAvailableByDateAndDepartment(LocalDate selectedDate, String department) {

        /*Basic data validations of empty or null inputs --START*/
        if(null == selectedDate){
            throw new RuntimeException("Kindly provide the date of appointment");
        }
        if(department.isBlank() || department.isEmpty()){
            throw new RuntimeException("Kindly provide the department of the health provider");
        }
        /*Basic data validations of empty or null inputs --END*/

        DayOfWeek dayOfWeek = selectedDate.getDayOfWeek();
        Long dayOfWeekValue = (long) dayOfWeek.getValue();


        return healthProviderRepository.findProvidersByDateAndDepartment(department, dayOfWeekValue);
    }
}
