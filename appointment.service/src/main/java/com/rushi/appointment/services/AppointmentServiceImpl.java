package com.rushi.appointment.services;

import com.rushi.appointment.configs.HealthProviderClient;
import com.rushi.appointment.dtos.HealthProvider;
import com.rushi.appointment.models.Appointment;
import com.rushi.appointment.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{


    private HealthProviderClient healthProviderClient;
    private AppointmentRepository appointmentRepository;

    @Override
    public String bookAppointment(LocalDate date, String hpDepartment) {

        try {

            List<HealthProvider> healthProviders = healthProviderClient.getAvailableHealthProvider(date,hpDepartment);

            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(date);
            appointment.setHpDepartment(hpDepartment);
            appointment.setHpName(healthProviders.get(0).getHpName());

            appointmentRepository.save(appointment);

            return "Your Appointment is confirmed.!";

        }catch (HttpClientErrorException ex){
            log.error("HTTP Error: Status Code: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString(), ex);
            return "Failed to book appointment: "+ ex.getMessage();
        }catch (Exception ex){
            log.error("Error occurred while booking an appointment", ex);
            return "An error occurred while booking an appointment: "+ ex.getMessage();
        }



    }
}
